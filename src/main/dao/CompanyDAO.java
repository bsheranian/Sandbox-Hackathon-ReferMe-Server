package dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import exception.SandboxSessionExpiredException;
import model.AuthToken;
import model.Company;
import util.HTTPRegex;

import java.sql.Timestamp;
import java.util.UUID;

public class CompanyDAO {

  private Table table;
  private final String TABLE_NAME = "company";
  private final String PRIMARY_KEY = "email";
  private final String NAME_FIELD = "name";
  private final String DESCRIPTION_FIELD = "description";
  private final String IMAGE_URL_FIELD = "image_url";


  public CompanyDAO() {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_WEST_2)
        .build();
    DynamoDB dynamoDB = new DynamoDB(client);
    this.table = dynamoDB.getTable(TABLE_NAME);
  }

  public void addCompany(Company newCompany) {
    System.out.println("Adding a new item...");
    PutItemOutcome outcome = table.putItem(new Item()
        .withPrimaryKey(PRIMARY_KEY, newCompany.getEmail())
        .withString(NAME_FIELD, newCompany.getName())
        .withString(DESCRIPTION_FIELD, newCompany.getDescription())
        .withString(IMAGE_URL_FIELD, newCompany.getWebsiteUrl()));
    System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
  }
}
