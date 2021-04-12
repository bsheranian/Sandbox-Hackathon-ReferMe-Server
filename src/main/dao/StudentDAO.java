package dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import exception.SandboxBadRequestException;
import exception.SandboxLoginException;
import exception.SandboxServerErrorException;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
import model.Mentor;
import model.Pair;
import model.Student;
import util.PasswordHasher;
import util.HTTPRegex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A DAO for accessing 'student' data from an AWS DynamoDB table.
 */
public class StudentDAO {

  private final Table table;
  private final String TABLE_NAME = "student";
  private final String PRIMARY_KEY = "email";
  private final String NAME_FIELD = "name";
  private final String SCHOOL_FIELD = "school";
  private final String IMAGE_URL_FIELD = "image_url";
  private final String MAJOR_FIELD = "major";
  private final String SORT_KEY = "industry";
  private final String GPA_FIELD = "gpa";


  public StudentDAO() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_WEST_2)
        .build();
    DynamoDB dynamoDB = new DynamoDB(client);
    this.table = dynamoDB.getTable(TABLE_NAME);
  }

  public void addStudent(Student newStudent) {
    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, newStudent.getEmail(), SORT_KEY, newStudent.getIndustry())
        .withString(NAME_FIELD, newStudent.getName())
        .withString(SCHOOL_FIELD, newStudent.getSchool())
        .withString(MAJOR_FIELD, newStudent.getMajor())
        .withDouble(GPA_FIELD, newStudent.getGpa())
        .withString(IMAGE_URL_FIELD, newStudent.getImageUrl()));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }


  public Student getStudent(String studentId, String industry) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, studentId, SORT_KEY, industry);
    System.out.println("Attempting to read the item...");
    Item item = table.getItem(spec);
    System.out.println("GetItem succeeded: " + item);

    Student newStudent = new Student();
    newStudent.setEmail(item.getString(PRIMARY_KEY));
    newStudent.setImageUrl(item.getString(IMAGE_URL_FIELD));
    newStudent.setName(item.getString(NAME_FIELD));
    newStudent.setSchool(item.getString(SCHOOL_FIELD));
    newStudent.setIndustry(item.getString(SORT_KEY));
    newStudent.setGpa(item.getFloat(GPA_FIELD));
    newStudent.setMajor(item.getString(MAJOR_FIELD));
    return newStudent;
  }


  public Pair<List<Student>, Boolean> getStudents(int limit, String last, String industry) {

    if (limit < 0) {
      throw new SandboxBadRequestException("[Bad Request]: Request size too small, size=" + limit);
    }

    int pageSize = limit;

    HashMap<String, String> nameMap = new HashMap<String, String>();
    nameMap.put("#industry", SORT_KEY);

    HashMap<String, Object> valueMap = new HashMap<String, Object>();
    valueMap.put(":industry", industry);

    Index index = table.getIndex("students-by-industry");

    QuerySpec querySpec;
    if (last == null || last.equals("null")) {
      querySpec = new QuerySpec()
          .withScanIndexForward(true)
          .withMaxResultSize(pageSize)
          .withKeyConditionExpression("#industry = :industry")
          .withNameMap(nameMap)
          .withValueMap(valueMap);
    } else {
      querySpec = new QuerySpec()
          .withScanIndexForward(true)
          .withMaxResultSize(pageSize)
          .withExclusiveStartKey(new PrimaryKey(PRIMARY_KEY, last, SORT_KEY, industry))
          .withKeyConditionExpression("#industry = :industry")
          .withNameMap(nameMap)
          .withValueMap(valueMap);
    }

    ItemCollection<QueryOutcome> items = index.query(querySpec);
    Iterator<Item> iterator = items.iterator();
    Item item = null;

    List<Student> studentList = new ArrayList<>();

    while (iterator.hasNext()) {
      item = iterator.next();

      Student newStudent = new Student();
      newStudent.setEmail(item.getString(PRIMARY_KEY));
      newStudent.setImageUrl(item.getString(IMAGE_URL_FIELD));
      newStudent.setName(item.getString(NAME_FIELD));
      newStudent.setSchool(item.getString(SCHOOL_FIELD));
      newStudent.setIndustry(item.getString(SORT_KEY));
      newStudent.setGpa(item.getFloat(GPA_FIELD));
      newStudent.setMajor(item.getString(MAJOR_FIELD));

      studentList.add(newStudent);
    }

    Map<String, AttributeValue> map = items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey();
    boolean hasMorePages = (map != null);

    return new Pair<>(studentList, hasMorePages);
  }


  /**
   * Deletes a user from the database.
   *
   * @param username the username of the user to delete
   */
  public void deleteStudent(String username, String industry) {
    DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
        .withPrimaryKey(new PrimaryKey(PRIMARY_KEY, username, SORT_KEY, industry));

    System.out.println("Attempting a conditional delete...");
    table.deleteItem(deleteItemSpec);
    System.out.println("DeleteItem succeeded");
  }


  /**
   * Deletes a user from the database.
   *
   * @param username the username of the user to delete
   */
  public void deleteStudent(String username) {
    DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
        .withPrimaryKey(new PrimaryKey(PRIMARY_KEY, username));

    System.out.println("Attempting a conditional delete...");
    table.deleteItem(deleteItemSpec);
    System.out.println("DeleteItem succeeded");
  }


//  /**
//   * Updates a user to contain the info in the updated user object.
//   *
//   * @param updatedUser the updated user object
//   */
//  public void updateUser(Student updatedUser) {
//
//    UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey(PRIMARY_KEY, updatedUser.getEmail())
//        .withUpdateExpression(String.format("set %s = :%s, %s = :%s, %s = :%s, %s = :%s, %s = :%s, %s = :%s",
//            PASSWORD_FIELD, PASSWORD_FIELD, ORGANIZATION_FIELD, ORGANIZATION_FIELD, FIRST_NAME_FIELD, FIRST_NAME_FIELD,
//            LAST_NAME_FIELD, LAST_NAME_FIELD, PHONE_NUMBER_FIELD, PHONE_NUMBER_FIELD, IS_RECRUITER_FIELD, IS_RECRUITER_FIELD))
//        .withValueMap(new ValueMap()
//            .withString(":" + PASSWORD_FIELD, updatedUser.getPassword())
//            .withString(":" + ORGANIZATION_FIELD, updatedUser.getOrganization())
//            .withString(":" + FIRST_NAME_FIELD, updatedUser.getFirstName())
//            .withString(":" + LAST_NAME_FIELD, updatedUser.getLastName())
//            .withString(":" + PHONE_NUMBER_FIELD, updatedUser.getPhone())
//            .withBoolean(":" + IS_RECRUITER_FIELD, updatedUser.isRecruiter()))
//        .withReturnValues(ReturnValue.UPDATED_NEW);
//
//    try {
//      System.out.println("Updating the item...");
//      UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
//      System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
//    } catch (Exception e) {
//      System.err.println(e.getMessage());
//      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + ": Unable to update user info");
//    }
//  }
}
