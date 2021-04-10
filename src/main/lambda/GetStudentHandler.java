package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetStudentRequest;
import response.GetStudentResponse;
import service.GetStudentService;

public class GetStudentHandler implements RequestHandler<GetStudentRequest, GetStudentResponse> {
  @Override
  public GetStudentResponse handleRequest(GetStudentRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering GetStudentHandler");
    logger.log("industry: " + request.getIndustry());
    logger.log("studentId: " + request.getStudentId());
    return new GetStudentService().doRequest(request);
  }
}
