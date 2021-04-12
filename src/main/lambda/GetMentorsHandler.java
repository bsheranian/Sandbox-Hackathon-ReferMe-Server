package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.GetMentorsRequest;
import response.GetMentorsResponse;
import service.GetMentorsService;

public class GetMentorsHandler implements RequestHandler<GetMentorsRequest, GetMentorsResponse> {
  @Override
  public GetMentorsResponse handleRequest(GetMentorsRequest request, Context context) {
    LambdaLogger logger = context.getLogger();
    logger.log("Entering GetMentorsHandler");
    logger.log("last: " + request.getLast());
    logger.log("limit: " + request.getLimit());
    logger.log("industry: " + request.getIndustry());

    return new GetMentorsService().doRequest(request);
  }


}
