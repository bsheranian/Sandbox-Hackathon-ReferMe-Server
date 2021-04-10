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
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;

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
  private final String TIME_STAMP_FIELD = "time_stamp";
  private final long HOUR = 3600000;
  private final long MAX_SESSION_DURATION = HOUR * 1;


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
        .withString(TIME_STAMP_FIELD, timestamp));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

    return new AuthToken(username, token);
  }

  public String getUsername(String token) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, token);

    System.out.println("Attempting to read the item...");
    Item outcome = table.getItem(spec);
    System.out.println("GetItem succeeded: " + outcome);

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
    String time = outcome.getString(TIME_STAMP_FIELD);

    Timestamp timestamp = Timestamp.valueOf(time);
    Timestamp now = new Timestamp(System.currentTimeMillis());

    boolean sessionActive = MAX_SESSION_DURATION > now.getTime() - timestamp.getTime();

    if (!sessionActive) {
      throw new SandboxSessionExpiredException(HTTPRegex.SESSION_EXPIRED);
    }
  }
}
