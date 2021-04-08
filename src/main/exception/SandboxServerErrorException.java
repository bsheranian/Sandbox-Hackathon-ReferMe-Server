package exception;

public class SandboxServerErrorException extends RuntimeException {

  private String message;

  public SandboxServerErrorException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
