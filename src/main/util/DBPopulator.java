package util;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import dao.CompanyDAO;
import dao.CredDAO;
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
import request.RegisterCompanyRequest;
import request.RegisterMentorRequest;
import request.RegisterStudentRequest;
import service.RegisterCompanyService;
import service.RegisterMentorService;
import service.RegisterStudentService;
import java.util.Random;



public class DBPopulator {

  static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
  static DynamoDB dynamoDB = new DynamoDB(client);

  private static String johnnyDeppImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.rottentomatoes.com%2Fcelebrity%2Fjohnny_depp&psig=AOvVaw1C3WqF5gdx7vC43v-CWYqy&ust=1618280303661000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKCh1onS9-8CFQAAAAAdAAAAABAD";
  private static String michaelJacksonImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.independent.co.uk%2Farts-entertainment%2Ftv%2Ffeatures%2Freal-michael-jackson-documentary-sex-abuse-allegations-leaving-neverland-a9434781.html&psig=AOvVaw3r3fjw9DCBvtjahGMjzfNQ&ust=1618280277506000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJCz3fzR9-8CFQAAAAAdAAAAABAD";
  private static String dwightSchruteImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.looper.com%2F157775%2Fthe-untold-truth-of-dwight-schrute%2F&psig=AOvVaw3xCwpyoFv1nCbD4ZwbMZZ1&ust=1618280249701000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLixou7R9-8CFQAAAAAdAAAAABAD";
  private static String markZuckerburgImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FMark_Zuckerberg&psig=AOvVaw1oGS6htxIdb9aEWcSufTA3&ust=1618280104956000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKiRu6nR9-8CFQAAAAAdAAAAABAD";
  private static String elonMuskImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FElon_Musk&psig=AOvVaw2lx4L-WPt165W58eB_i21P&ust=1618280084134000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCMjH_p7R9-8CFQAAAAAdAAAAABAD";
  private static String markRoberImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fyoutube.fandom.com%2Fwiki%2FMark_Rober&psig=AOvVaw0XNw7yEACOtn2bbHqnIJUg&ust=1618280124658000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPDa0LPR9-8CFQAAAAAdAAAAABAD";
  private static String steveJobsImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FSteve_Jobs&psig=AOvVaw2cblrU7LAg-Flilum8LciZ&ust=1618280141192000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCOC407vR9-8CFQAAAAAdAAAAABAD";
  private static String kanyeWestImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.bloomberg.com%2Fnews%2Farticles%2F2020-09-10%2Fkanye-west-calls-out-gap-adidas-over-black-board-seats&psig=AOvVaw107QEzdbrZo-bjIlxPWsjV&ust=1618280165194000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIjnt8fR9-8CFQAAAAAdAAAAABAD";
  private static String lebronJamesImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fnymag.com%2Fintelligencer%2F2020%2F09%2Flebron-james-is-now-at-the-center-of-everything.html&psig=AOvVaw1MP-I_VmJMsLpXpyIZTcNv&ust=1618280182765000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCODtss7R9-8CFQAAAAAdAAAAABAP";
  private static String michaelScottImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FMichael_Scott_(The_Office)&psig=AOvVaw1_yDd8oxr35GWMuwxlijsB&ust=1618280220390000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCOij9eDR9-8CFQAAAAAdAAAAABAD";
  private static String theRockImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vanityfair.com%2Fstyle%2F2020%2F09%2Fdwayne-the-rock-johnson-family-coronavirus-diagnosis&psig=AOvVaw3J-jvZ8A9SwfdbHe1rqA6p&ust=1618278656863000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCMjC8vrL9-8CFQAAAAAdAAAAABAD";
  private static String googleLogoImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.facebook.com%2FGoogle%2F&psig=AOvVaw04M0ZFNWfQUlHlUkcjJ_k1&ust=1618278823990000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLiLrcbM9-8CFQAAAAAdAAAAABAD";
  private static String justinBieberImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.billboard.com%2Farticles%2Fcolumns%2Fpop%2F9475340%2Fjustin-bieber-next-chapter-documentary-revealing-moments%2F&psig=AOvVaw3Y-V5DUkYRuHd_Hw2U9xNH&ust=1618278717168000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCMiTjZPM9-8CFQAAAAAdAAAAABAD";
  private static String amazonLogoImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F8585055522171072%2F&psig=AOvVaw3-qMnWPvXUAcxAa2T3iWgP&ust=1618288517373000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCLCRntfw9-8CFQAAAAAdAAAAABAD";
  private static String microsoftLogoImage = "https://www.google.com/url?sa=i&url=https%3A%2F%2Flogos-world.net%2Fmicrosoft-logo%2F&psig=AOvVaw0YQhFfl5OIpJP65VOYOSfr&ust=1618288540547000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCMjEmeDw9-8CFQAAAAAdAAAAABAR";
  private static String cImage = "";
  private static String dImage = "";
  private static String eImage = "";
  private static String fImage = "";
  private static String gImage = "";
  private static String hImage = "";
  private static String iImage = "";
  private static String jImage = "";
  private static String kImage = "";




  public static void main(String args[]) {


    Random rand = new Random();

    new CredDAO().deleteCred("studentdemo@referme.com");
    new CredDAO().deleteCred("mentordemo@referme.com");
    new CredDAO().deleteCred("employerdemo@referme.com");

    new StudentDAO().deleteStudent("studentdemo@referme.com", "demo");
    new MentorDAO().deleteMentor("mentordemo@referme.com", "demo");
    new CompanyDAO().deleteCompany("employerdemo@referme.com");

    new RegisterStudentService().doRequest(new RegisterStudentRequest(new Student("studentdemo@referme.com", "demo", theRockImage, "Nathan Wallace", "BYU", "Computer Science", "demo", 4)));
    new RegisterCompanyService().doRequest(new RegisterCompanyRequest(new Company("employerdemo@referme.com", "demo", googleLogoImage, "Google", "https://about.google/", "Our mission is to organize the world’s information and make it universally accessible and useful.")));
    new RegisterMentorService().doRequest(new RegisterMentorRequest(new Mentor("mentordemo@referme.com", "demo", justinBieberImage, "Harrison Sligting", "BYU", "demo", 5.0, 10, 7845.34)));

    new MentorDAO().addMentor(new Mentor("demomentor1@referme.com", null, johnnyDeppImage, "Harrison Sligting", "BYU", "demo", rand.nextDouble() + rand.nextInt(2) + 3, (rand.nextInt(15)) + 3, rand.nextDouble() + rand.nextInt(100000)));
    new MentorDAO().addMentor(new Mentor("demomentor2@referme.com", null, michaelJacksonImage, "Tanner Moulton", "BYU", "demo", rand.nextDouble() + rand.nextInt(2) + 3, (rand.nextInt(15)) + 3, rand.nextDouble() + rand.nextInt(100000)));
    new MentorDAO().addMentor(new Mentor("demomentor3@referme.com", null, dwightSchruteImage, "Bryan Sheranian", "UVU", "demo", rand.nextDouble() + rand.nextInt(2) + 3, (rand.nextInt(15)) + 3, rand.nextDouble() + rand.nextInt(100000)));
    new MentorDAO().addMentor(new Mentor("demomentor4@referme.com", null, markZuckerburgImage, "Mark Zuckerberg", "UVU", "demo", rand.nextDouble() + rand.nextInt(2) + 3, (rand.nextInt(15)) + 3, rand.nextDouble() + rand.nextInt(100000)));
    new MentorDAO().addMentor(new Mentor("demomentor5@referme.com", null, elonMuskImage, "Elon Musk", "BYU", "demo", rand.nextDouble() + rand.nextInt(2) + 3, (rand.nextInt(15)) + 3, rand.nextDouble() + rand.nextInt(100000)));
    new MentorDAO().addMentor(new Mentor("demomentor6@referme.com", null, markRoberImage, "Mark Rober", "USU", "demo", rand.nextDouble() + rand.nextInt(2) + 3, (rand.nextInt(15)) + 3, rand.nextDouble() + rand.nextInt(100000)));
    new MentorDAO().addMentor(new Mentor("demomentor7@referme.com", null, steveJobsImage, "Steve Jobs", "USU", "demo", rand.nextDouble() + rand.nextInt(2) + 3, (rand.nextInt(15)) + 3, rand.nextDouble() + rand.nextInt(100000)));
    new MentorDAO().addMentor(new Mentor("demomentor8@referme.com", null, kanyeWestImage, "Kanye West", "USU", "demo", rand.nextDouble() + rand.nextInt(2) + 3, (rand.nextInt(15)) + 3, rand.nextDouble() + rand.nextInt(100000)));
    new MentorDAO().addMentor(new Mentor("demomentor9@referme.com", null, lebronJamesImage, "Lebron James", "BYU", "demo", rand.nextDouble() + rand.nextInt(2) + 3, (rand.nextInt(15)) + 3, rand.nextDouble() + rand.nextInt(100000)));
    new MentorDAO().addMentor(new Mentor("demomentor10@referme.com", null, michaelScottImage, "Michael Scott", "BYU", "demo", rand.nextDouble() + rand.nextInt(2) + 3, (rand.nextInt(15)) + 3, rand.nextDouble() + rand.nextInt(100000)));


    new MatchDAO().addMatch(new Match("studentdemo@referme.com", "demomentor3@referme.com", "accepted"));
    new MatchDAO().addMatch(new Match("studentdemo@referme.com", "demomentor4@referme.com", "accepted"));
    new MatchDAO().addMatch(new Match("studentdemo@referme.com", "demomentor5@referme.com", "studentdemo@referme.com"));
    new MatchDAO().addMatch(new Match("studentdemo@referme.com", "demomentor6@referme.com", "studentdemo@referme.com"));
    new MatchDAO().addMatch(new Match("studentdemo@referme.com", "demomentor7@referme.com", "studentdemomentor7@referme.com"));
    new MatchDAO().addMatch(new Match("studentdemo@referme.com", "demomentor8@referme.com", "studentdemomentor8@referme.com"));

    new StudentDAO().addStudent(new Student("demostudent1@referme.com", null, johnnyDeppImage, "Johnny Depp", "BYU", "Computer Science", "demo", rand.nextDouble() + 3.0));
    new StudentDAO().addStudent(new Student("demostudent2@referme.com", null, michaelJacksonImage, "Michael Jackson", "BYU", "Computer Science", "demo", rand.nextDouble() + 3.0));
    new StudentDAO().addStudent(new Student("demostudent3@referme.com", null, dwightSchruteImage, "Dwight Schrute", "BYU", "UX Design", "demo", rand.nextDouble() + 3.0));
    new StudentDAO().addStudent(new Student("demostudent4@referme.com", null, markZuckerburgImage, "Mark Zuckerburg", "BYU", "Computer Science", "demo", rand.nextDouble() + 3.0));
    new StudentDAO().addStudent(new Student("demostudent5@referme.com", null, elonMuskImage, "Elon Musk", "BYU", "UX Design", "demo", rand.nextDouble() + 3.0));
    new StudentDAO().addStudent(new Student("demostudent6@referme.com", null, steveJobsImage, "Steve Jobs", "UVU", "Computer Science", "demo", rand.nextDouble() + 3.0));
    new StudentDAO().addStudent(new Student("demostudent7@referme.com", null, markRoberImage, "Mark Rober", "UVU", "UX Design", "demo", rand.nextDouble() + 3.0));
    new StudentDAO().addStudent(new Student("demostudent8@referme.com", null, michaelScottImage, "Michael Scott", "USU", "Computer Science", "demo", rand.nextDouble() + 3.0));
    new StudentDAO().addStudent(new Student("demostudent9@referme.com", null, kanyeWestImage, "Kanye West", "USU", "UX Design", "demo", rand.nextDouble() + 3.0));
    new StudentDAO().addStudent(new Student("demostudent10@referme.com", null, lebronJamesImage, "Lebron James", "USU", "Information Systems", "demo", rand.nextDouble() + 3.0));

    new MatchDAO().addMatch(new Match("demostudent1@referme.com", "mentordemo@referme.com", "accepted"));
    new MatchDAO().addMatch(new Match("demostudent2@referme.com", "mentordemo@referme.com", "accepted"));
    new MatchDAO().addMatch(new Match("demostudent3@referme.com", "mentordemo@referme.com", "accepted"));
    new MatchDAO().addMatch(new Match("demostudent4@referme.com", "mentordemo@referme.com", "accepted"));
    new MatchDAO().addMatch(new Match("demostudent5@referme.com", "mentordemo@referme.com", "demostudent5@referme.com"));
    new MatchDAO().addMatch(new Match("demostudent6@referme.com", "mentordemo@referme.com", "demostudent6@referme.com"));

    new JobOpeningDAO().addJobOpening(new JobOpening("demoGoogleOpening1", "demo", "Quality Assurance Engineer", "employerdemo@referme.com"));
    new JobOpeningDAO().addJobOpening(new JobOpening("demoGoogleOpening2", "demo", "Software Integration Engineer", "employerdemo@referme.com"));
    new JobOpeningDAO().addJobOpening(new JobOpening("demoGoogleOpening3", "demo", "Front-end Engineer", "employerdemo@referme.com"));
    new JobOpeningDAO().addJobOpening(new JobOpening("demoGoogleOpening4", "demo", "3D Graphics Developer", "employerdemo@referme.com"));


    new JobOpeningDAO().addJobOpening(new JobOpening("demoAmazonOpening1", "demo", "QA Engineer", "demoemployer1@referme.com"));
    new JobOpeningDAO().addJobOpening(new JobOpening("demoAmazonOpening2", "demo", "Data Scientist", "demoemployer1@referme.com"));
    new JobOpeningDAO().addJobOpening(new JobOpening("demoAmazonOpening3", "demo", "AI Engineer", "demoemployer1@referme.com"));

    new JobOpeningDAO().addJobOpening(new JobOpening("demoMicrosoftOpening1", "demo", "Machine Learning Engineer", "demoemployer2@referme.com"));
    new JobOpeningDAO().addJobOpening(new JobOpening("demoMicrosoftOpening2", "demo", "Software Engineer Level II", "demoemployer2@referme.com"));



    new CompanyDAO().addCompany(new Company("demoemployer1@referme.com", "demo", amazonLogoImage, "Amazon", "https://www.aboutamazon.com/", "We aim to be Earth's most customer centric company. Our mission is to continually raise the bar of the customer experience by using the internet and technology to help consumers find, discover and buy anything, and empower businesses and content creators to maximise their success."));
    new CompanyDAO().addCompany(new Company("demoemployer2@referme.com", "demo", microsoftLogoImage, "Microsoft", "https://www.microsoft.com/en-us/about", "Our mission is to empower every person and every organization on the planet to achieve more"));

    new RecommendationDAO().addRecommendation(new Recommendation("demoRecommendation1","Johnny Depp is a great Student. I think he'll be a great fit","demomentor7@referme.com","demostudent1@referme.com","demoGoogleOpening1"));
    new RecommendationDAO().addRecommendation(new Recommendation("demoRecommendation2","I've worked with Michael for the past year and he has a lot of grit. I think he would do great.","demomentor8@referme.com","demostudent2@referme.com","demoGoogleOpening2"));
    new RecommendationDAO().addRecommendation(new Recommendation("demoRecommendation3","Dwight is the man for the job. He has been assistant to the regional manager for a long time.","demomentor9@referme.com","demostudent3@referme.com","demoGoogleOpening2"));
    new RecommendationDAO().addRecommendation(new Recommendation("demoRecommendation4","Mark has a lot of industry experience. I think he'd be a perfect fit for the job","demomentor10@referme.com","demostudent4@referme.com","demoGoogleOpening3"));
    new RecommendationDAO().addRecommendation(new Recommendation("demoRecommendation5","Elon is a little crazy but gets the job done no matter what and has a lot of creativity and grit.","demomentor7@referme.com","demostudent5@referme.com","demoGoogleOpening3"));
    new RecommendationDAO().addRecommendation(new Recommendation("demoRecommendation6","Steve talks about apples a lot but his front-end work is absolutely gorgeous. He's your man for the job.","demomentor8@referme.com","demostudent6@referme.com","demoGoogleOpening3"));

  }

}
