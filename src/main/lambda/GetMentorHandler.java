package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetMentorRequest;
import response.GetMentorResponse;
import service.GetMentorService;

public class GetMentorHandler implements RequestHandler<GetMentorRequest, GetMentorResponse> {
  @Override
  public GetMentorResponse handleRequest(GetMentorRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering GetJobOpeningsHandler");
    logger.log("industry: " + request.getIndustry());
    logger.log("mentorId: " + request.getMentorId());
    return new GetMentorService().doRequest(request);
  }
}
