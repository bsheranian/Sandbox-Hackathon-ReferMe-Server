package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetMyStudentsRequest;
import response.GetMyStudentsResponse;

public class GetMyStudentsHandler implements RequestHandler<GetMyStudentsRequest, GetMyStudentsResponse> {
  @Override
  public GetMyStudentsResponse handleRequest(GetMyStudentsRequest getMyStudentsRequest, Context context) {
    return null;
  }
}
