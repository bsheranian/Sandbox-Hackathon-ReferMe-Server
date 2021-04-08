package exception;

public class SandboxServerErrorException extends RuntimeException {
  public SandboxServerErrorException(String message) {
    super(message);
  }
}
