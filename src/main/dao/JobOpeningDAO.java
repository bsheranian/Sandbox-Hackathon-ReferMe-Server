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

public class JobOpeningDAO {

  private Table table;
  private final String TABLE_NAME = "job_opening";
  private final String PRIMARY_KEY = "id";
  private final String DESCRIPTION_FIELD = "description";
  private final String SORT_KEY = "industry";


  public JobOpeningDAO() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_WEST_2)
        .build();
    DynamoDB dynamoDB = new DynamoDB(client);
    this.table = dynamoDB.getTable(TABLE_NAME);
  }

  public void addJobOpening(JobOpening newOpening) {
    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, UUID.randomUUID().toString(), SORT_KEY, newOpening.getIndustry())
        .withString(DESCRIPTION_FIELD, newOpening.getJobDescription()));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }

  public Pair<List<JobOpening>, Boolean> getJobOpenings(int limit, String last, String industry) {

    if (limit < 0) {
      throw new SandboxBadRequestException("[Bad Request]: Request size too small, size=" + limit);
    }
    if (last == null) {
      throw new SandboxBadRequestException("[Bad Request]: Invalid follower id, id=" + last);
    }

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

    List<JobOpening> jobOpenings = new ArrayList<>();

    while (iterator.hasNext()) {
      item = iterator.next();

      JobOpening jobOpening = new JobOpening();
      jobOpening.setId(item.getString(PRIMARY_KEY));
      jobOpening.setIndustry(industry);
      jobOpening.setJobDescription(item.getString(DESCRIPTION_FIELD));

      jobOpenings.add(jobOpening);
    }

    Map<String, AttributeValue> map = items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey();
    boolean hasMorePages = (map != null);

    return new Pair<>(jobOpenings, hasMorePages);
  }

}
