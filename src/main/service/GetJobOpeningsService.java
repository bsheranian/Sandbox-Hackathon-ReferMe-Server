package service;

import exception.SandboxServerErrorException;
import model.JobOpening;
import model.Pair;
import request.GetJobOpeningsRequest;
import response.GetJobOpeningsResponse;
import util.HTTPRegex;

import java.util.ArrayList;
import java.util.List;

public class GetJobOpeningsService extends ServiceTemplate<GetJobOpeningsRequest, GetJobOpeningsResponse> {
    @Override
    public GetJobOpeningsResponse doRequest(GetJobOpeningsRequest request) {
        boolean hasMorePages;
        List<JobOpening> jobOpenings = new ArrayList<>();

        try {
            Pair<List<JobOpening>, Boolean> outcome = getJobOpeningDAO().getJobOpenings(request.getLimit(), request.getLast(), request.getIndustry());
            hasMorePages = outcome.getSecond();
            jobOpenings = outcome.getFirst();
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new GetJobOpeningsResponse(jobOpenings, hasMorePages);
    }
}
