package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.IsMatchedRequest;
import response.IsMatchedResponse;

public class IsMatchedHandler implements RequestHandler<IsMatchedRequest, IsMatchedResponse> {
  @Override
  public IsMatchedResponse handleRequest(IsMatchedRequest isMatchedRequest, Context context) {
    return null;
  }
}
