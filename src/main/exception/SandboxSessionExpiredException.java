package exception;

public class SandboxSessionExpiredException extends RuntimeException {

  private String message;

  public SandboxSessionExpiredException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
