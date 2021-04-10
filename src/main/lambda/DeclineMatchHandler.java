package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.DeclineMatchRequest;
import response.DeclineMatchResponse;

public class DeclineMatchHandler implements RequestHandler<DeclineMatchRequest, DeclineMatchResponse> {
  @Override
  public DeclineMatchResponse handleRequest(DeclineMatchRequest declineMatchRequest, Context context) {
    return null;
  }
}
