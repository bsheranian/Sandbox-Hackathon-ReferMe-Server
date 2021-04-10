package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetMentorsRequest;
import response.GetMentorsResponse;

public class GetMentorsHandler implements RequestHandler<GetMentorsRequest, GetMentorsResponse> {
  @Override
  public GetMentorsResponse handleRequest(GetMentorsRequest getMentorsRequest, Context context) {
    return null;
  }
}
