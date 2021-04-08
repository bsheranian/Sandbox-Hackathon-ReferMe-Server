package exception;

public class SandboxUsernameAlreadyTakenException extends RuntimeException {

  private String message;
  public SandboxUsernameAlreadyTakenException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
