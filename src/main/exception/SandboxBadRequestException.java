package exception;

public class SandboxBadRequestException extends RuntimeException {
  private String message;
  public SandboxBadRequestException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
