package request;

import model.Mentor;

public class RegisterMentorRequest {
  private Mentor newMentor;

  public RegisterMentorRequest() {}

  public RegisterMentorRequest(Mentor newMentor) {
    this.newMentor = newMentor;
  }

  public Mentor getNewMentor() {
    return newMentor;
  }

  public void setNewMentor(Mentor newMentor) {
    this.newMentor = newMentor;
  }
}
