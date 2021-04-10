package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.RecommendStudentRequest;
import response.RecommendStudentResponse;
import service.RecommendStudentService;

public class RecommendStudentHandler implements RequestHandler<RecommendStudentRequest, RecommendStudentResponse> {
  @Override
  public RecommendStudentResponse handleRequest(RecommendStudentRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering RecommendStudentHandler");
    logger.log("jobOpeningId: " + request.getJobOpeningId());
    logger.log("mentorEmail: " + request.getMentorEmail());
    logger.log("studentEmail: " + request.getStudentEmail());
    logger.log("message: " + request.getMessage());
    return new RecommendStudentService().doRequest(request);
  }
}
