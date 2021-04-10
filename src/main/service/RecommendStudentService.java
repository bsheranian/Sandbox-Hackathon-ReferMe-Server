package service;

import exception.SandboxServerErrorException;
import model.Recommendation;
import request.RecommendStudentRequest;
import response.RecommendStudentResponse;
import util.HTTPRegex;

import java.util.UUID;

public class RecommendStudentService extends ServiceTemplate<RecommendStudentRequest, RecommendStudentResponse>{
    @Override
    public RecommendStudentResponse doRequest(RecommendStudentRequest request) {
        try {
            Recommendation newRecommendation = new Recommendation(UUID.randomUUID().toString(), request.getMessage(),
                request.getMentorEmail(), request.getStudentEmail(), request.getJobOpeningId());
            getRecommendationDAO().addRecommendation(newRecommendation);
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new RecommendStudentResponse("Successfully Recommended Student", true);
    }
}
