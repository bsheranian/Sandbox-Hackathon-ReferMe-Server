package dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
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
import exception.SandboxLoginException;
import exception.SandboxServerErrorException;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
import model.User;
import util.PasswordHasher;
import util.HTTPRegex;

/**
 * A DAO for accessing 'user' data from an AWS DynamoDB table.
 */
public class UserDAO implements IUserDAO {

  private final Table table;
  private final String TABLE_NAME = "user";
  private final String PRIMARY_KEY = "email";
  private final String PASSWORD_FIELD = "password";
  private final String ORGANIZATION_FIELD = "organization";
  private final String FIRST_NAME_FIELD = "first_name";
  private final String LAST_NAME_FIELD = "last_name";
  private final String PHONE_NUMBER_FIELD = "phone_number";
  private final String IS_RECRUITER_FIELD = "is_recruiter";


  public UserDAO() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_WEST_2)
        .build();
    DynamoDB dynamoDB = new DynamoDB(client);
    this.table = dynamoDB.getTable(TABLE_NAME);
  }


  /**
   * Registers a new user in the database.
   *
   * @param newUser the user to be registered
   */
  @Override
  public void registerUser(User newUser) {

    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, newUser.getEmail());
    System.out.println("Attempting to read the item...");
    Item item = table.getItem(spec);
    System.out.println("GetItem succeeded: " + item);

    if (item != null) {
      throw new SandboxEmailAlreadyAssociatedWithUserException(HTTPRegex.EMAIL_TAKEN);
    }

    String hashedPassword;
    try {
      hashedPassword = PasswordHasher.generateHashedPassword(newUser.getPassword());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Unable to hash password");
    }

    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, newUser.getEmail())
        .withString(PASSWORD_FIELD, hashedPassword)
        .withString(ORGANIZATION_FIELD, newUser.getOrganization())
        .withString(FIRST_NAME_FIELD, newUser.getFirstName())
        .withString(LAST_NAME_FIELD, newUser.getLastName())
        .withString(PHONE_NUMBER_FIELD, newUser.getPhone())
        .withBoolean(IS_RECRUITER_FIELD, newUser.isRecruiter()));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }


  /**
   * Validates that a username and password match.
   *
   * @param username the username in question
   * @param password the password in question
   * @return true if the username and password match
   */
  @Override
  public boolean validateUserCredentials(String username, String password) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, username);

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


  /**
   * Deletes a user from the database.
   *
   * @param username the username of the user to delete
   */
  @Override
  public void deleteUser(String username) {
    DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
        .withPrimaryKey(new PrimaryKey(PRIMARY_KEY, username));

    System.out.println("Attempting a conditional delete...");
    table.deleteItem(deleteItemSpec);
    System.out.println("DeleteItem succeeded");
  }


  /**
   * Updates a user to contain the info in the updated user object.
   *
   * @param updatedUser the updated user object
   */
  @Override
  public void updateUser(User updatedUser) {

    UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey(PRIMARY_KEY, updatedUser.getEmail())
        .withUpdateExpression(String.format("set %s = :%s, %s = :%s, %s = :%s, %s = :%s, %s = :%s, %s = :%s",
            PASSWORD_FIELD, PASSWORD_FIELD, ORGANIZATION_FIELD, ORGANIZATION_FIELD, FIRST_NAME_FIELD, FIRST_NAME_FIELD,
            LAST_NAME_FIELD, LAST_NAME_FIELD, PHONE_NUMBER_FIELD, PHONE_NUMBER_FIELD, IS_RECRUITER_FIELD, IS_RECRUITER_FIELD))
        .withValueMap(new ValueMap()
            .withString(":" + PASSWORD_FIELD, updatedUser.getPassword())
            .withString(":" + ORGANIZATION_FIELD, updatedUser.getOrganization())
            .withString(":" + FIRST_NAME_FIELD, updatedUser.getFirstName())
            .withString(":" + LAST_NAME_FIELD, updatedUser.getLastName())
            .withString(":" + PHONE_NUMBER_FIELD, updatedUser.getPhone())
            .withBoolean(":" + IS_RECRUITER_FIELD, updatedUser.isRecruiter()))
        .withReturnValues(ReturnValue.UPDATED_NEW);

    try {
      System.out.println("Updating the item...");
      UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
      System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
    } catch (Exception e) {
      System.err.println(e.getMessage());
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Unable to update user info");
    }
  }
}
