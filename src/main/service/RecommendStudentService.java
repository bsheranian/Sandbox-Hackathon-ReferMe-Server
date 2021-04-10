package service;

import exception.SandboxServerErrorException;
import model.Recommendation;
import request.recommendStudentRequest;
import response.recommendStudentResponse;
import util.HTTPRegex;

import java.util.UUID;

public class RecommendStudentService extends ServiceTemplate<recommendStudentRequest, recommendStudentResponse>{
    @Override
    public recommendStudentResponse doRequest(recommendStudentRequest request) {
        try {
            Recommendation newRecommendation = new Recommendation(UUID.randomUUID().toString(), request.getMessage(),
                request.getMentorEmail(), request.getStudentEmail(), request.getJobOpeningId());
            getRecommendationDAO().addRecommendation(newRecommendation);
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new recommendStudentResponse("Successfully Recommended Student", true);
    }
}
