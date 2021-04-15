package dao;

import java.sql.Timestamp;
import java.util.UUID;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;

import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import exception.SandboxServerErrorException;
import exception.SandboxSessionExpiredException;
import model.AuthToken;
import util.HTTPRegex;

/**
 * A DAO for accessing 'auth' data from an AWS DynamoDB table.
 */
public class AuthDAO {

  private Table table;
  private final String TABLE_NAME = "auth";
  private final String PRIMARY_KEY = "token";
  private final String USERNAME_FIELD = "username";
  private final String TIME_CREATED_FIELD = "time_created";
  private final String LAST_USED_FIELD = "last_used";

  private final long WEEK = 604800000;
  private final long DAY = 86400000;
  private final long HOUR = 3600000;
  private final long MINUTE = 60000;

  private final long MAX_SESSION_DURATION = HOUR * 24;
  private final long MAX_DURATION_BETWEEN_USES = HOUR * 2;


  public AuthDAO() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_WEST_2)
        .build();
    DynamoDB dynamoDB = new DynamoDB(client);
    this.table = dynamoDB.getTable(TABLE_NAME);
  }


  /**
   * Creates an auth token for the specified user.
   *
   * @param username the user to be log in
   * @return the auth token
   */
  public AuthToken createAuthToken(String username) {
    String timestamp = new Timestamp(System.currentTimeMillis()).toString();
    String token = username + "-" + UUID.randomUUID().toString();

    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, token)
        .withString(USERNAME_FIELD, username)
        .withString(LAST_USED_FIELD, timestamp)
        .withString(TIME_CREATED_FIELD, timestamp));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

    return new AuthToken(username, token);
  }

  public String getUsername(String token) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, token);

    System.out.println("Attempting to read the item...");
    Item outcome = table.getItem(spec);
    System.out.println("GetItem succeeded: " + outcome);

    updateLastUsedTime(token);

    return outcome.getString(USERNAME_FIELD);
  }




  /**
   * Deletes the authToken in the database.
   *
   * @param token the token to be deleted
   */
  public void deleteToken(String token) {
    DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
        .withPrimaryKey(new PrimaryKey(PRIMARY_KEY, token));

    System.out.println("Attempting a conditional delete...");
    DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);
    System.out.println("DeleteItem succeeded");
  }


  public void updateLastUsedTime(String token) {

    String currentTime = new Timestamp(System.currentTimeMillis()).toString();

    UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey(PRIMARY_KEY, token)
        .withUpdateExpression(String.format("set %s = :%s", LAST_USED_FIELD, LAST_USED_FIELD))
        .withValueMap(new ValueMap()
            .withString(":" + LAST_USED_FIELD, currentTime))
        .withReturnValues(ReturnValue.UPDATED_NEW);

    try {
      System.out.println("Updating the item...");
      UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
      System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
    } catch (Exception e) {
      System.err.println(e.getMessage());
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Unable to update auth token timestamp");
    }
  }

  /**
   * Validates that the auth token exists and hasn't timed out.
   *
   * @param token the token to be verified
   */
  public void validateToken(String token) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, token);

    System.out.println("Attempting to read the item...");
    Item outcome = table.getItem(spec);
    System.out.println("GetItem succeeded: " + outcome);

    Timestamp now = new Timestamp(System.currentTimeMillis());

    String timeCreated = outcome.getString(TIME_CREATED_FIELD);
    Timestamp timestamp = Timestamp.valueOf(timeCreated);
    boolean sessionActive = MAX_SESSION_DURATION > now.getTime() - timestamp.getTime();

    String timeLastUsed = outcome.getString(LAST_USED_FIELD);
    timestamp = Timestamp.valueOf(timeLastUsed);
    boolean recentUseOfToken = MAX_DURATION_BETWEEN_USES > now.getTime() - timestamp.getTime();


    if (!sessionActive || !recentUseOfToken) {
      throw new SandboxSessionExpiredException(HTTPRegex.SESSION_EXPIRED);
    } else {
      updateLastUsedTime(token);
    }
  }
}
