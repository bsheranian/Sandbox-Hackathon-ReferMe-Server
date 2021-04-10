package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetStudentsRequest;
import response.GetStudentsResponse;

public class GetStudentsHandler implements RequestHandler<GetStudentsRequest, GetStudentsResponse> {
  @Override
  public GetStudentsResponse handleRequest(GetStudentsRequest getStudentsRequest, Context context) {
    return null;
  }
}
