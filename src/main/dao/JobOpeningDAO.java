package dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import exception.SandboxEmailAlreadyAssociatedWithUserException;
import exception.SandboxServerErrorException;
import model.JobOpening;
import util.HTTPRegex;
import util.PasswordHasher;

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

}
