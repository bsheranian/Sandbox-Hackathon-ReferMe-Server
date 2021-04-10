package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.AcceptMatchRequest;
import response.AcceptMatchResponse;
import service.AcceptMatchService;

public class AcceptMatchHandler implements RequestHandler<AcceptMatchRequest, AcceptMatchResponse> {
  @Override
  public AcceptMatchResponse handleRequest(AcceptMatchRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering AcceptMatchHandler");
    logger.log("studentId: " + request.getStudentId());
    logger.log("mentorId: " + request.getMentorId());
    return new AcceptMatchService().doRequest(request);
  }
}
