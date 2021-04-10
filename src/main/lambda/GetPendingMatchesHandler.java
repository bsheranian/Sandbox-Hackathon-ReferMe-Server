package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetPendingMatchesRequest;
import response.GetPendingMatchesResponse;

public class GetPendingMatchesHandler implements RequestHandler<GetPendingMatchesRequest, GetPendingMatchesResponse> {
  @Override
  public GetPendingMatchesResponse handleRequest(GetPendingMatchesRequest getPendingMatchesRequest, Context context) {
    return null;
  }
}
