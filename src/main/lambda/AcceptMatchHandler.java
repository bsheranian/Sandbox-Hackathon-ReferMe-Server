package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.AcceptMatchRequest;
import response.AcceptMatchResponse;

public class AcceptMatchHandler implements RequestHandler<AcceptMatchRequest, AcceptMatchResponse> {
  @Override
  public AcceptMatchResponse handleRequest(AcceptMatchRequest acceptMatchRequest, Context context) {
    return null;
  }
}
