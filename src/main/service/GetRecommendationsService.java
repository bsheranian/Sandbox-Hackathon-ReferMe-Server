package service;

import exception.SandboxServerErrorException;
import model.Pair;
import model.Recommendation;
import request.GetRecommendationsRequest;
import response.GetRecommendationsResponse;
import util.HTTPRegex;

import java.util.ArrayList;
import java.util.List;

public class GetRecommendationsService extends ServiceTemplate<GetRecommendationsRequest, GetRecommendationsResponse>{
    @Override
    public GetRecommendationsResponse doRequest(GetRecommendationsRequest request) {
        boolean hasMorePages;
        List<Recommendation> recommendations = new ArrayList<>();

        try {
            Pair<List<Recommendation>, Boolean> outcome = getRecommendationDAO().getRecommendations(request.getLimit(), request.getLast(), request.getOpeningId());
            hasMorePages = outcome.getSecond();
            recommendations = outcome.getFirst();
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new GetRecommendationsResponse(recommendations, hasMorePages);
    }
}
