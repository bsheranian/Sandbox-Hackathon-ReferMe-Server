package dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
import exception.SandboxLoginException;
import exception.SandboxServerErrorException;
import util.HTTPRegex;
import util.PasswordHasher;

public class CredDAO {

  private Table table;
  private final String TABLE_NAME = "credential";
  private final String PRIMARY_KEY = "email";
  private final String PASSWORD_FIELD = "password";
  private final String SORT_KEY = "user_type";


  public CredDAO() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_WEST_2)
        .build();
    DynamoDB dynamoDB = new DynamoDB(client);
    this.table = dynamoDB.getTable(TABLE_NAME);
  }

  public void registerCredentials(String username, String password, int userType) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, username, SORT_KEY, userType);
    System.out.println("Attempting to read the item...");
    Item item = table.getItem(spec);
    System.out.println("GetItem succeeded: " + item);

    if (item != null) {
      throw new SandboxEmailAlreadyAssociatedWithUserException(HTTPRegex.EMAIL_TAKEN);
    }

    String hashedPassword;
    try {
      hashedPassword = PasswordHasher.generateHashedPassword(password);
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Unable to hash password");
    }

    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, username, SORT_KEY, userType)
        .withString(PASSWORD_FIELD, hashedPassword));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }


  public boolean validateUserCredentials(String username, String password, int userType) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, username, SORT_KEY, userType);

    System.out.println("Attempting to read the item...");
    Item outcome = table.getItem(spec);
    System.out.println("GetItem succeeded: " + outcome);

    if (outcome == null) {
      throw new SandboxLoginException(HTTPRegex.INCORRECT_USERNAME);
    }

    String storedPassword = outcome.getString(PASSWORD_FIELD);
    boolean verified;

    try {
      verified = PasswordHasher.validatePassword(password, storedPassword);
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Unable to verify password");
    }

    return verified;
  }


}
