package dao;

import java.io.IOException;
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
import util.HTTP;

/**
 * A DAO for accessing 'auth' data from the database.
 */
public class AuthDAO implements IAuthDAO {

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
   * @param username the user to log in
   * @return the auth token
   * @throws IOException if there is an IOException
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

  /**
   * Logs out the user's current session
   * @param token the token to be deleted
   * @return the logout response
   */
  public void deleteToken(String token) {
    DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
        .withPrimaryKey(new PrimaryKey(PRIMARY_KEY, token));

    System.out.println("Attempting a conditional delete...");
    DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);
    System.out.println("DeleteItem succeeded");
  }


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
      throw new SandboxSessionExpiredException(HTTP.SESSION_EXPIRED);
    }
  }
}
