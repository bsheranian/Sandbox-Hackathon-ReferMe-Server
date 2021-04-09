package exception;

public class SandboxEmailAlreadyAssociatedWithUserException extends RuntimeException {

  private String message;
  public SandboxEmailAlreadyAssociatedWithUserException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
