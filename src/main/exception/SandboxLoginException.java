package exception;

public class SandboxLoginException extends RuntimeException {

  private String message;

  public SandboxLoginException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }


}
