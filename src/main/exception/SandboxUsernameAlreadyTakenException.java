package exception;

public class SandboxUsernameAlreadyTakenException extends RuntimeException {
  public SandboxUsernameAlreadyTakenException(String message) {
    super(message);
  }
}
