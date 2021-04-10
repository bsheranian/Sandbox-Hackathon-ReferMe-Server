package util;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;
import dao.JobOpeningDAO;
import dao.MatchDAO;
import dao.MentorDAO;
import dao.RecommendationDAO;
import dao.StudentDAO;
import model.Company;
import model.JobOpening;
import model.Match;
import model.Mentor;
import model.Recommendation;
import model.Student;
import request.GetJobOpeningsRequest;
import request.GetMyMentorsRequest;
import request.GetMyStudentsRequest;
import request.GetPendingMatchesRequest;
import request.GetRecommendationRequest;
import request.GetRecommendationsRequest;
import request.LoginRequest;
import request.RegisterRequest;
import service.GetJobOpeningsService;
import service.GetMyMentorsService;
import service.GetMyStudentsService;
import service.GetPendingMatchesService;
import service.GetRecommendationService;
import service.GetRecommendationsService;
import service.LoginService;
import service.RecommendStudentService;
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
//
//
//    new RegisterService().doRequest(new RegisterRequest(new Company("@Google", "password", "imageUrl", "Google", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@LinkedIn", "password", "imageUrl", "LinkedIn", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@Glassdoor", "password", "imageUrl", "GlassDoor", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@HandShake", "password", "imageUrl", "Handshake", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@BYUConnect", "password", "imageUrl", "BYUConnect", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@BYU", "password", "imageUrl", "BYU", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@UVU", "password", "imageUrl", "BYU", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@UoU", "password", "imageUrl", "UVU", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@USU", "password", "imageUrl", "UoU", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@Apple", "password", "imageUrl", "Apple", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@Samsung", "password", "imageUrl", "Samsung", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@Amazon", "password", "imageUrl", "Amazon", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@Microsoft", "password", "imageUrl", "Microsoft", "website", "decription") ,2));
//    new RegisterService().doRequest(new RegisterRequest(new Company("@DOMO", "password", "imageUrl", "DOMO", "website", "decription") ,2));
//

//    new MatchDAO().addMatch(new Match("@Bryan", "@Nathan", "accepted"));
//    new MatchDAO().addMatch(new Match("@Bryan", "@Nate", "@Bryan"));
//    new MatchDAO().addMatch(new Match("@Bryan", "@Alex", "@Bryan"));
//    new MatchDAO().addMatch(new Match("@Bryan", "@Harrison", "@Bryan"));
//    new MatchDAO().addMatch(new Match("@Bryan", "@Tanner", "@Bryan"));
//    new MatchDAO().addMatch(new Match("@Bryan", "@Eric", "@Bryan"));


    //new GetMyMentorsService().doRequest(new GetMyMentorsRequest(null, "industry", 7, "@Bryan-04583bd6-70f4-4ad5-bc4e-bc1c87bd309b"));

    //new GetMyStudentsService().doRequest(new GetMyStudentsRequest(null, 7, "@Nathan-c8746906-ff33-4ccb-894f-a02058ccdb6a", "industry"));

    //new GetJobOpeningsService().doRequest(new GetJobOpeningsRequest(null, 7, "industry"));

    // new LoginService().doRequest(new LoginRequest("@Bryan", "password", 0));

    new GetRecommendationsService().doRequest(new GetRecommendationsRequest("id=1", 30, null));


//    new GetPendingMatchesService().doRequest(new GetPendingMatchesRequest(null, 30, "@Nathan-c8746906-ff33-4ccb-894f-a02058ccdb6a", "industry"));
//    for (int i = 0; i < 75; i++) {
//      new JobOpeningDAO().addJobOpening(new JobOpening("id=" + i, "industry", "description" + i));
//      for (int j = 0; j < 75; j++) {
//        new RecommendationDAO().addRecommendation(new Recommendation("id=" + (i + 1000000), "message" + j, "mentor@email", "student@email", "id=" + i));
//      }
//    }
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
