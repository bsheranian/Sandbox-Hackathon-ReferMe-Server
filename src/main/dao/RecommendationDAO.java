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
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import exception.SandboxBadRequestException;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
import exception.SandboxServerErrorException;
import model.JobOpening;
import model.Pair;
import model.Recommendation;
import model.Student;
import util.HTTPRegex;
import util.PasswordHasher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RecommendationDAO {

  private Table table;
  private final String TABLE_NAME = "recommendation";
  private final String PRIMARY_KEY = "id";
  private final String MESSAGE_FIELD = "message";
  private final String MENTOR_EMAIL_FIELD = "mentor_email";
  private final String STUDENT_EMAIL_FIELD = "student_email";
  private final String SORT_KEY = "job_opening_id";


  public RecommendationDAO() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_WEST_2)
        .build();
    DynamoDB dynamoDB = new DynamoDB(client);
    this.table = dynamoDB.getTable(TABLE_NAME);
  }

  public void addRecommendation(Recommendation newRecommendation) {
    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, UUID.randomUUID().toString(), SORT_KEY, newRecommendation.getJobOpeningId())
        .withString(MENTOR_EMAIL_FIELD, newRecommendation.getMentorEmail())
        .withString(STUDENT_EMAIL_FIELD, newRecommendation.getStudentEmail())
        .withString(MESSAGE_FIELD, newRecommendation.getMessage()));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }


  public Pair<List<Recommendation>, Boolean> getRecommendations(int limit, String last, String jobOpeningId) {

    if (limit < 0) {
      throw new SandboxBadRequestException("[Bad Request]: Request size too small, size=" + limit);
    }
    if (last == null) {
      throw new SandboxBadRequestException("[Bad Request]: Invalid follower id, id=" + last);
    }

    int pageSize = limit;

    HashMap<String, String> nameMap = new HashMap<String, String>();
    nameMap.put("#opening", SORT_KEY);

    HashMap<String, Object> valueMap = new HashMap<String, Object>();
    valueMap.put(":opening", jobOpeningId);

    QuerySpec querySpec;
    if (last == null || last.equals("null")) {
      querySpec = new QuerySpec()
          .withScanIndexForward(true)
          .withMaxResultSize(pageSize)
          .withKeyConditionExpression("#opening = :opening")
          .withNameMap(nameMap)
          .withValueMap(valueMap);
    } else {
      querySpec = new QuerySpec()
          .withScanIndexForward(true)
          .withMaxResultSize(pageSize)
          .withExclusiveStartKey(new PrimaryKey(PRIMARY_KEY, last, SORT_KEY, jobOpeningId))
          .withKeyConditionExpression("#opening = :opening")
          .withNameMap(nameMap)
          .withValueMap(valueMap);
    }

    ItemCollection<QueryOutcome> items = table.query(querySpec);
    Iterator<Item> iterator = items.iterator();
    Item item = null;

    List<Recommendation> recommendations = new ArrayList<>();

    while (iterator.hasNext()) {
      item = iterator.next();

      Recommendation newRecommendation = new Recommendation();
      newRecommendation.setId(item.getString(PRIMARY_KEY));
      newRecommendation.setMentorEmail(item.getString(MENTOR_EMAIL_FIELD));
      newRecommendation.setStudentEmail(item.getString(STUDENT_EMAIL_FIELD));
      newRecommendation.setMessage(item.getString(MESSAGE_FIELD));
      newRecommendation.setJobOpeningId(item.getString(SORT_KEY));

      recommendations.add(newRecommendation);
    }

    Map<String, AttributeValue> map = items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey();
    boolean hasMorePages = (map != null);

    return new Pair<>(recommendations, hasMorePages);
  }


}

