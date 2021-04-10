package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.PostJobOpeningRequest;
import response.PostJobOpeningResponse;

public class PostJobOpeningHandler implements RequestHandler<PostJobOpeningRequest, PostJobOpeningResponse> {
  @Override
  public PostJobOpeningResponse handleRequest(PostJobOpeningRequest postJobOpeningRequest, Context context) {
    return null;
  }
}
