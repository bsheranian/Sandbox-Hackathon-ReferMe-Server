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
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import exception.SandboxBadRequestException;
import exception.SandboxServerErrorException;
import model.Mentor;
import model.Pair;
import model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
  private final String YEARS_EXPERIENCE_FIELD = "years_experience";

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
        .withInt(YEARS_EXPERIENCE_FIELD, newMentor.getYearsExperience())
        .withString(IMAGE_URL_FIELD, newMentor.getImageUrl()));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }


  public Mentor getMentor(String mentorId, String industry) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, mentorId, SORT_KEY, industry);
    System.out.println("Attempting to read the item...");
    Item item = table.getItem(spec);
    System.out.println("GetItem succeeded: " + item);
    Mentor newMentor = new Mentor();
    newMentor.setEmail(item.getString(PRIMARY_KEY));
    newMentor.setImageUrl(item.getString(IMAGE_URL_FIELD));
    newMentor.setName(item.getString(NAME_FIELD));
    newMentor.setSchool(item.getString(SCHOOL_FIELD));
    newMentor.setIndustry(item.getString(SORT_KEY));
    newMentor.setRating(item.getFloat(RATING_FIELD));
    newMentor.setMoneyMade(item.getFloat(MONEY_FIELD));
    newMentor.setYearsExperience(item.getInt(YEARS_EXPERIENCE_FIELD));

    return newMentor;
  }



  public void deleteMentor(String username, String industry) {
    DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
        .withPrimaryKey(new PrimaryKey(PRIMARY_KEY, username, SORT_KEY, industry));
    System.out.println("Attempting a conditional delete...");
    table.deleteItem(deleteItemSpec);
    System.out.println("DeleteItem succeeded");
  }


  public Pair<List<Mentor>, Boolean> getMentors(int limit, String last, String industry) {

    if (limit < 0) {
      throw new SandboxBadRequestException("[Bad Request]: Request size too small, size=" + limit);
    }

    if (last == null) {
      throw new SandboxBadRequestException("[Bad Request]: Invalid follower id, id=" + last);
    }

    List<String> followeeAliases = new ArrayList<>();
    int pageSize = limit;


    HashMap<String, String> nameMap = new HashMap<String, String>();
    nameMap.put("#industry", SORT_KEY);

    HashMap<String, Object> valueMap = new HashMap<String, Object>();
    valueMap.put(":industry", industry);

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

    ItemCollection<QueryOutcome> items = table.query(querySpec);
    Iterator<Item> iterator = items.iterator();
    Item item = null;

    List<Mentor> mentorsList = new ArrayList<>();

    while (iterator.hasNext()) {
      item = iterator.next();

      Mentor newMentor = new Mentor();
      newMentor.setEmail(item.getString(PRIMARY_KEY));
      newMentor.setImageUrl(item.getString(IMAGE_URL_FIELD));
      newMentor.setName(item.getString(NAME_FIELD));
      newMentor.setSchool(item.getString(SCHOOL_FIELD));
      newMentor.setIndustry(item.getString(SORT_KEY));
      newMentor.setRating(item.getFloat(RATING_FIELD));
      newMentor.setMoneyMade(item.getFloat(MONEY_FIELD));
      newMentor.setYearsExperience(item.getInt(YEARS_EXPERIENCE_FIELD));

      mentorsList.add(newMentor);
    }

    Map<String, AttributeValue> map = items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey();
    boolean hasMorePages = (map != null);

    return new Pair<List<Mentor>, Boolean>(mentorsList, hasMorePages);
  }
}
