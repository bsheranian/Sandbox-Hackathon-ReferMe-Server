package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetJobOpeningRequest;
import response.GetJobOpeningResponse;
import service.GetJobOpeningService;

public class GetJobOpeningHandler implements RequestHandler<GetJobOpeningRequest, GetJobOpeningResponse> {
  @Override
  public GetJobOpeningResponse handleRequest(GetJobOpeningRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering GetJobOpeningHandler");
    logger.log("token: " + request.getToken());
    logger.log("industry: " + request.getIndustry());
    logger.log("jobOpeningId: " + request.getJobOpeningId());
    return new GetJobOpeningService().doRequest(request);
  }
}
