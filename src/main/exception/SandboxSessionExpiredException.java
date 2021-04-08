package exception;

public class SandboxSessionExpiredException extends RuntimeException {
  public SandboxSessionExpiredException(String message) {
    super(message);
  }
}
