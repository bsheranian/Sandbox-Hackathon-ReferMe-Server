package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.DeclineMatchRequest;
import response.DeclineMatchResponse;
import service.DeclineMatchService;

public class DeclineMatchHandler implements RequestHandler<DeclineMatchRequest, DeclineMatchResponse> {
  @Override
  public DeclineMatchResponse handleRequest(DeclineMatchRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering DeclineMatchHandler");
    logger.log("mentorId: " + request.getMentorId());
    logger.log("studentId: " + request.getStudentId());
    return new DeclineMatchService().doRequest(request);
  }
}
