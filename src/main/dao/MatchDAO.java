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
import com.amazonaws.services.dynamodbv2.xspec.S;
import model.JobOpening;
import model.Match;

import java.util.UUID;

public class MatchDAO {

  private Table table;
  private final String TABLE_NAME = "match";
  private final String PRIMARY_KEY = "student_id";
  private final String STATUS_FIELD = "status";
  private final String SORT_KEY = "mentor_id";
  private final String ACCEPTED = "accepted";

  public MatchDAO() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_WEST_2)
        .build();
    DynamoDB dynamoDB = new DynamoDB(client);
    this.table = dynamoDB.getTable(TABLE_NAME);
  }

  public void addMatch(String currUserID, Match newMatch) {
    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, newMatch.getStudentId(), SORT_KEY, newMatch.getMentorId())
        .withString(STATUS_FIELD, currUserID));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }

  public void deleteMatch(String studentId, String mentorId) {
    DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
        .withPrimaryKey(new PrimaryKey(PRIMARY_KEY, studentId, SORT_KEY, mentorId));
    System.out.println("Attempting a conditional delete...");
    table.deleteItem(deleteItemSpec);
    System.out.println("DeleteItem succeeded");
  }

  public void acceptMatch(String studentId, String mentorId) {
    UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey(PRIMARY_KEY, studentId, SORT_KEY, mentorId)
        .withUpdateExpression(String.format("set %s = :%s", STATUS_FIELD, STATUS_FIELD))
        .withValueMap(new ValueMap().withString(":" + STATUS_FIELD, ACCEPTED))
        .withReturnValues(ReturnValue.UPDATED_NEW);
    System.out.println("Updating the item...");
    UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
    System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
  }

  public boolean areMatched(String studentId, String mentorId) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, studentId, SORT_KEY, mentorId);

    System.out.println("Attempting to read the item...");
    Item outcome = table.getItem(spec);
    System.out.println("GetItem succeeded: " + outcome);

    if (outcome == null) {
      return false;
    }

    return outcome.getString(STATUS_FIELD).equals(ACCEPTED);
  }
}
