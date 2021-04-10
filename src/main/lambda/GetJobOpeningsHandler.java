package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetJobOpeningsRequest;
import response.GetJobOpeningsResponse;

public class GetJobOpeningsHandler implements RequestHandler<GetJobOpeningsRequest, GetJobOpeningsResponse> {
  @Override
  public GetJobOpeningsResponse handleRequest(GetJobOpeningsRequest getJobOpeningsRequest, Context context) {
    return null;
  }
}
