package service;

import exception.SandboxServerErrorException;
import model.JobOpening;
import model.Pair;
import model.User;
import request.getOpeningsRequest;
import response.getOpeningsResponse;
import response.getPendingMatchesResponse;
import util.HTTPRegex;
import util.UserType;

import java.util.ArrayList;
import java.util.List;

public class GetOpeningsService extends ServiceTemplate<getOpeningsRequest, getOpeningsResponse> {
    @Override
    public getOpeningsResponse doRequest(getOpeningsRequest request) {
        boolean hasMorePages;
        List<JobOpening> jobOpenings = new ArrayList<>();

        try {
            Pair<List<JobOpening>, Boolean> outcome = getJobOpeningDAO().getJobOpenings(request.getLimit(), request.getLast(), request.getIndustry());
            hasMorePages = outcome.getSecond();
            jobOpenings = outcome.getFirst();
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new getOpeningsResponse(jobOpenings, hasMorePages);
    }
}
