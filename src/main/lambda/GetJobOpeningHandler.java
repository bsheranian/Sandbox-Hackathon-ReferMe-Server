package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetJobOpeningRequest;
import response.GetJobOpeningResponse;

public class GetJobOpeningHandler implements RequestHandler<GetJobOpeningRequest, GetJobOpeningResponse> {
  @Override
  public GetJobOpeningResponse handleRequest(GetJobOpeningRequest getJobOpeningRequest, Context context) {
    return null;
  }
}
