package dao;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import exception.SandboxLoginException;
import exception.SandboxServerErrorException;
import exception.SandboxUsernameAlreadyTakenException;
import model.User;
import util.PasswordHasher;

public class UserDAO implements IUserDAO {

  private Table table;
  private final String TABLE_NAME = "user";
  private final String PRIMARY_KEY = "username";
  private final String PASSWORD_FIELD = "password";
  private final String EMAIL_FIELD = "email";


  public UserDAO() {
    ClientConfiguration clientConfig = new ClientConfiguration();
    clientConfig.setSocketTimeout(150000);
    clientConfig.setConnectionTimeout(15000);
    clientConfig.setMaxErrorRetry(2);

    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion("us-west-2")
        .withClientConfiguration(clientConfig)
        .build();

    DynamoDB dynamoDB = new DynamoDB(client);
    this.table = dynamoDB.getTable(TABLE_NAME);
  }

  @Override
  public void registerUser(User newUser) {

    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, newUser.getUsername());
    System.out.println("Attempting to read the item...");
    Item item = table.getItem(spec);
    System.out.println("GetItem succeeded: " + item);

    if (item != null) {
      throw new SandboxUsernameAlreadyTakenException("[Username Already Taken]");
    }

    String hashedPassword;
    try {
      hashedPassword = PasswordHasher.generateHashedPassword(newUser.getPassword());
    } catch (Exception e) {
      throw new SandboxServerErrorException("[Server Error]: Unable to hash password");
    }

    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, newUser.getUsername())
        .withString(PASSWORD_FIELD, hashedPassword)
        .withString(EMAIL_FIELD, newUser.getEmail()));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }

  @Override
  public boolean validateUserCredentials(String username, String password) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, username);

    System.out.println("Attempting to read the item...");
    Item outcome = table.getItem(spec);
    System.out.println("GetItem succeeded: " + outcome);

    if (outcome == null) {
      throw new SandboxLoginException("[Incorrect Username]");
    }

    String storedPassword = outcome.getString(PASSWORD_FIELD);
    boolean verified;

    try {
      verified = PasswordHasher.validatePassword(password, storedPassword);
    } catch (Exception e) {
      throw new SandboxServerErrorException("[Server Error]: Unable to verify password");
    }

    return verified;
  }
}
