package util;

import dao.MentorDAO;
import model.Mentor;

public class DBPopulator {

  public static void main(String args[]) {
    new MentorDAO().addMentor(new Mentor("@Alaric", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000));
    new MentorDAO().addMentor(new Mentor("@Alex", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000));
    new MentorDAO().addMentor(new Mentor("@Eric", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000));
    new MentorDAO().addMentor(new Mentor("@Harrison", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000));
    new MentorDAO().addMentor(new Mentor("@Nate", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000));
    new MentorDAO().addMentor(new Mentor("@Nathan", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000));
    new MentorDAO().addMentor(new Mentor("@Tanner", "password", "imageUrl", "name", "school", "industry", 5, 100, 1000));

  }
}
