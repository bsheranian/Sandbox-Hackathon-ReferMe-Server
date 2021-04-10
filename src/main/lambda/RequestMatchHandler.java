package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.RequestMatchRequest;
import response.RequestMatchResponse;

public class RequestMatchHandler implements RequestHandler<RequestMatchRequest, RequestMatchResponse> {
  @Override
  public RequestMatchResponse handleRequest(RequestMatchRequest requestMatchRequest, Context context) {
    return null;
  }
}
