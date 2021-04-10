package util;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import dao.JobOpeningDAO;
import dao.MentorDAO;
import dao.RecommendationDAO;
import dao.StudentDAO;
import model.JobOpening;
import model.Mentor;
import model.Recommendation;
import model.Student;
import request.RegisterRequest;
import service.RegisterService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class DBPopulator {

  static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
  static DynamoDB dynamoDB = new DynamoDB(client);

  public static void main(String args[]) {

//    new RegisterService().doRequest(new RegisterRequest(new Mentor("@Alex", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000),1));
//    new RegisterService().doRequest(new RegisterRequest(new Mentor("@Eric", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000),1));
//    new RegisterService().doRequest(new RegisterRequest(new Mentor("@Aaron", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000),1));
//    new RegisterService().doRequest(new RegisterRequest(new Mentor("@Tanner", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000),1));
//    new RegisterService().doRequest(new RegisterRequest(new Mentor("@Nathan", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000),1));
//    new RegisterService().doRequest(new RegisterRequest(new Mentor("@Harrison", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000),1));
//    new RegisterService().doRequest(new RegisterRequest(new Mentor("@Gabriel", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000),1));
//    new RegisterService().doRequest(new RegisterRequest(new Mentor("@Matt", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000),1));
//
//
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Bryan", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Tommy", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Becky", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Ricky", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Timmy", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Lizzy", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Katy", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Emmy", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Ally", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Amy", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));
//    new RegisterService().doRequest(new RegisterRequest(new Student("@Billy", "password", "imageUrl", "name", "school", "major", "industry", 4) ,0));

    for (int i = 0; i < 75; i++) {
      new JobOpeningDAO().addJobOpening(new JobOpening("id=" + i, "industry", "description" + i));
      for (int j = 0; j < 75; j++) {
        new RecommendationDAO().addRecommendation(new Recommendation("id=" + (i + 1000000), "message" + j, "mentor@email", "student@email", "id=" + i));
      }
    }
  }

  private static void writeMultipleItemsBatchWrite() {
    try {

      // Add a new item to Forum
      TableWriteItems forumTableWriteItems = new TableWriteItems("match") // Forum
          .withItemsToPut(new Item().withPrimaryKey("student_id", "mentor_id").withString("status", "accepted"));


      System.out.println("Making the request.");
      BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(forumTableWriteItems);

      do {

        // Check for unprocessed keys which could happen if you exceed
        // provisioned throughput

        Map<String, List<WriteRequest>> unprocessedItems = outcome.getUnprocessedItems();

        if (outcome.getUnprocessedItems().size() == 0) {
          System.out.println("No unprocessed items found");
        }
        else {
          System.out.println("Retrieving the unprocessed items");
          outcome = dynamoDB.batchWriteItemUnprocessed(unprocessedItems);
        }

      } while (outcome.getUnprocessedItems().size() > 0);

    }
    catch (Exception e) {
      System.err.println("Failed to retrieve items: ");
      e.printStackTrace(System.err);
    }

  }

}
