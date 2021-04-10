package dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import model.Mentor;
import model.Student;

public class MentorDAO {

  private final Table table;
  private final String TABLE_NAME = "mentor";
  private final String PRIMARY_KEY = "email";
  private final String NAME_FIELD = "name";
  private final String SCHOOL_FIELD = "school";
  private final String IMAGE_URL_FIELD = "image_url";
  private final String SORT_KEY = "industry";
  private final String RATING_FIELD = "gpa";
  private final String MONEY_FIELD = "money";

  public MentorDAO() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_WEST_2)
        .build();
    DynamoDB dynamoDB = new DynamoDB(client);
    this.table = dynamoDB.getTable(TABLE_NAME);
  }

  public void addMentor(Mentor newMentor) {
    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, newMentor.getEmail(), SORT_KEY, newMentor.getIndustry())
        .withString(NAME_FIELD, newMentor.getName())
        .withString(SCHOOL_FIELD, newMentor.getSchool())
        .withFloat(MONEY_FIELD, newMentor.getMoneyMade())
        .withFloat(RATING_FIELD, newMentor.getRating())
        .withString(IMAGE_URL_FIELD, newMentor.getImageUrl()));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }




  public void deleteMentor(String username, String industry) {
    DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
        .withPrimaryKey(new PrimaryKey(PRIMARY_KEY, username, SORT_KEY, industry));
    System.out.println("Attempting a conditional delete...");
    table.deleteItem(deleteItemSpec);
    System.out.println("DeleteItem succeeded");
  }
}
