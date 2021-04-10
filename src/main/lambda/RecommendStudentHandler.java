package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.RecommendStudentRequest;
import response.RecommendStudentResponse;

public class RecommendStudentHandler implements RequestHandler<RecommendStudentRequest, RecommendStudentResponse> {
  @Override
  public RecommendStudentResponse handleRequest(RecommendStudentRequest recommendStudentRequest, Context context) {
    return null;
  }
}
