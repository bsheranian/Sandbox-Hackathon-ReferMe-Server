package service;

import exception.SandboxServerErrorException;
import model.JobOpening;
import model.Pair;
import model.Recommendation;
import request.getRecommendationsRequest;
import response.getOpeningsResponse;
import response.getRecommendationsResponse;
import util.HTTPRegex;

import java.util.ArrayList;
import java.util.List;

public class GetRecommendationsService extends ServiceTemplate<getRecommendationsRequest, getRecommendationsResponse>{
    @Override
    public getRecommendationsResponse doRequest(getRecommendationsRequest request) {
        boolean hasMorePages;
        List<Recommendation> recommendations = new ArrayList<>();

        try {
            Pair<List<Recommendation>, Boolean> outcome = getRecommendationDAO().getRecommendations(request.getLimit(), request.getLast(), request.getOpeningId());
            hasMorePages = outcome.getSecond();
            recommendations = outcome.getFirst();
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new getRecommendationsResponse(recommendations, hasMorePages);
    }
}
