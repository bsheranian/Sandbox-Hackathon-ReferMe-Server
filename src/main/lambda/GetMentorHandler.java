package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetMentorRequest;
import response.GetMentorResponse;

public class GetMentorHandler implements RequestHandler<GetMentorRequest, GetMentorResponse> {
  @Override
  public GetMentorResponse handleRequest(GetMentorRequest getMentorRequest, Context context) {
    return null;
  }
}
