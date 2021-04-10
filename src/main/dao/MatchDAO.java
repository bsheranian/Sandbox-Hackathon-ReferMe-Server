package dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
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
import com.amazonaws.services.dynamodbv2.xspec.S;
import exception.SandboxBadRequestException;
import model.JobOpening;
import model.Match;
import model.Mentor;
import model.Pair;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

  public void addMatch(Match newMatch) {
    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, newMatch.getStudentId(), SORT_KEY, newMatch.getMentorId())
        .withString(STATUS_FIELD, newMatch.getStatus()));
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


  public boolean userRequestedMatch(String studentId, String mentorId, boolean userIsStudent) {

    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, studentId, SORT_KEY, mentorId);

    System.out.println("Attempting to read the item...");
    Item outcome = table.getItem(spec);
    System.out.println("GetItem succeeded: " + outcome);

    if (outcome == null) {
      return false;
    }

    if (userIsStudent) {
      return outcome.getString(STATUS_FIELD).equals(studentId);
    } else {
      return outcome.getString(STATUS_FIELD).equals(mentorId);
    }
  }

  public Pair<List<String>, Boolean> getMentors(int limit, String lastMentorId, String studentId) {

    if (limit < 0) {
      throw new SandboxBadRequestException("[Bad Request]: Request size too small, size=" + limit);
    }
    if (lastMentorId == null) {
      throw new SandboxBadRequestException("[Bad Request]: Invalid mentorId, mentorId=" + lastMentorId);
    }
    if (studentId == null) {
      throw new SandboxBadRequestException("[Bad Request]: Invalid studentId, studentId=" + studentId);
    }

    int pageSize = limit;

    HashMap<String, String> nameMap = new HashMap<String, String>();
    nameMap.put("#studentId", PRIMARY_KEY);

    HashMap<String, Object> valueMap = new HashMap<String, Object>();
    valueMap.put(":studentId", studentId);

    System.out.println("No error before query");

    QuerySpec querySpec;
    if (lastMentorId == null || lastMentorId.equals("null")) {
      querySpec = new QuerySpec()
          .withScanIndexForward(true)
          .withMaxResultSize(pageSize)
          .withKeyConditionExpression("#studentId = :studentId")
          .withNameMap(nameMap)
          .withValueMap(valueMap);
    } else {
      querySpec = new QuerySpec()
          .withScanIndexForward(true)
          .withMaxResultSize(pageSize)
          .withExclusiveStartKey(new PrimaryKey(PRIMARY_KEY, studentId, SORT_KEY, lastMentorId))
          .withKeyConditionExpression("#studentId = :studentId")
          .withNameMap(nameMap)
          .withValueMap(valueMap);
    }

    System.out.println("No error after query");

    ItemCollection<QueryOutcome> items = table.query(querySpec);
    Iterator<Item> iterator = items.iterator();
    Item item = null;

    List<String> mentorIds = new ArrayList<>();

    while (iterator.hasNext()) {
      item = iterator.next();
      mentorIds.add(item.getString(SORT_KEY));
    }

    Map<String, AttributeValue> map = items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey();
    boolean hasMorePages = (map != null);

    return new Pair<List<String>, Boolean>(mentorIds, hasMorePages);
  }




  public Pair<List<String>, Boolean> getStudents(int limit, String lastStudentId, String mentorId) {

    if (limit < 0) {
      throw new SandboxBadRequestException("[Bad Request]: Request size too small, size=" + limit);
    }
    if (lastStudentId == null) {
      throw new SandboxBadRequestException("[Bad Request]: Invalid mentorId, mentorId=" + lastStudentId);
    }
    if (mentorId == null) {
      throw new SandboxBadRequestException("[Bad Request]: Invalid studentId, studentId=" + mentorId);
    }

    int pageSize = limit;

    HashMap<String, String> nameMap = new HashMap<String, String>();
    nameMap.put("#mentorId", SORT_KEY);

    HashMap<String, Object> valueMap = new HashMap<String, Object>();
    valueMap.put(":mentorId", mentorId);

    System.out.println("No error before query");

    QuerySpec querySpec;
    if (lastStudentId == null || lastStudentId.equals("null")) {
      querySpec = new QuerySpec()
          .withScanIndexForward(true)
          .withMaxResultSize(pageSize)
          .withKeyConditionExpression("#mentorId = :mentorId")
          .withNameMap(nameMap)
          .withValueMap(valueMap);
    } else {
      querySpec = new QuerySpec()
          .withScanIndexForward(true)
          .withMaxResultSize(pageSize)
          .withExclusiveStartKey(new PrimaryKey(PRIMARY_KEY, lastStudentId, SORT_KEY, mentorId))
          .withKeyConditionExpression("#mentorId = :mentorId")
          .withNameMap(nameMap)
          .withValueMap(valueMap);
    }

    System.out.println("No error after query");

    ItemCollection<QueryOutcome> items = table.query(querySpec);
    Iterator<Item> iterator = items.iterator();
    Item item = null;

    List<String> studentIds = new ArrayList<>();

    while (iterator.hasNext()) {
      item = iterator.next();
      studentIds.add(item.getString(SORT_KEY));
    }

    Map<String, AttributeValue> map = items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey();
    boolean hasMorePages = (map != null);

    return new Pair<>(studentIds, hasMorePages);
  }
}
