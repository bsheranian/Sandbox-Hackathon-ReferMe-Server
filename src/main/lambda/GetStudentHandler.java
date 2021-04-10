package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetStudentRequest;
import response.GetStudentResponse;

public class GetStudentHandler implements RequestHandler<GetStudentRequest, GetStudentResponse> {
  @Override
  public GetStudentResponse handleRequest(GetStudentRequest getStudentRequest, Context context) {
    return null;
  }
}
