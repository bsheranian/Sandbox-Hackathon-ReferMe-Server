package service;

import exception.SandboxServerErrorException;
import model.JobOpening;
import request.GetJobOpeningRequest;
import response.GetJobOpeningResponse;
import util.HTTPRegex;

public class GetJobOpeningService extends ServiceTemplate<GetJobOpeningRequest, GetJobOpeningResponse> {
  @Override
  public GetJobOpeningResponse doRequest(GetJobOpeningRequest request) {
    JobOpening jobOpening;
    try {
      jobOpening = getJobOpeningDAO().getJobOpening(request.getJobOpeningId(), request.getIndustry());
    } catch (Exception e) {
      throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
    }

    return new GetJobOpeningResponse(jobOpening);
  }
}
