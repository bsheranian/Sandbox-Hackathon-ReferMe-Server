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
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import exception.SandboxBadRequestException;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
import exception.SandboxServerErrorException;
import model.JobOpening;
import model.Mentor;
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
  private final String COMPANY_ID_FIELD = "company_id";
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
        .withPrimaryKey(PRIMARY_KEY, newOpening.getId(), SORT_KEY, newOpening.getIndustry())
        .withString(COMPANY_ID_FIELD, newOpening.getCompanyId())
        .withString(DESCRIPTION_FIELD, newOpening.getJobDescription()));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }

  public JobOpening getJobOpening(String jobOpeningId, String industry) {
    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PRIMARY_KEY, jobOpeningId, SORT_KEY, industry);
    System.out.println("Attempting to read the item...");
    Item item = table.getItem(spec);
    System.out.println("GetItem succeeded: " + item);
    JobOpening jobOpening = new JobOpening();
    jobOpening.setId(item.getString(PRIMARY_KEY));
    jobOpening.setIndustry(industry);
    jobOpening.setCompanyId(item.getString(COMPANY_ID_FIELD));
    jobOpening.setJobDescription(item.getString(DESCRIPTION_FIELD));

    return jobOpening;
  }

  public Pair<List<JobOpening>, Boolean> getJobOpenings(int limit, String last, String industry) {

    if (limit < 0) {
      throw new SandboxBadRequestException("[Bad Request]: Request size too small, size=" + limit);
    }

    int pageSize = limit;

    HashMap<String, String> nameMap = new HashMap<String, String>();
    nameMap.put("#industry", SORT_KEY);

    HashMap<String, Object> valueMap = new HashMap<String, Object>();
    valueMap.put(":industry", industry);

    Index index = table.getIndex("openings-by-industry");

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

    List<JobOpening> jobOpenings = new ArrayList<>();

    while (iterator.hasNext()) {
      item = iterator.next();

      JobOpening jobOpening = new JobOpening();
      jobOpening.setId(item.getString(PRIMARY_KEY));
      jobOpening.setIndustry(industry);
      jobOpening.setCompanyId(item.getString(COMPANY_ID_FIELD));
      jobOpening.setJobDescription(item.getString(DESCRIPTION_FIELD));

      jobOpenings.add(jobOpening);
    }

    Map<String, AttributeValue> map = items.getLastLowLevelResult().getQueryResult().getLastEvaluatedKey();
    boolean hasMorePages = (map != null);

    return new Pair<>(jobOpenings, hasMorePages);
  }

}
