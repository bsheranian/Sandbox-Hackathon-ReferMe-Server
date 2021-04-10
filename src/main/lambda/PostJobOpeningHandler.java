package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.PostJobOpeningRequest;
import response.PostJobOpeningResponse;
import service.PostJobOpeningService;

public class PostJobOpeningHandler implements RequestHandler<PostJobOpeningRequest, PostJobOpeningResponse> {
  @Override
  public PostJobOpeningResponse handleRequest(PostJobOpeningRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering PostJobOpeningHandler");
    logger.log("jobOpening: " + request.getJobOpening());
    return new PostJobOpeningService().doRequest(request);
  }
}
