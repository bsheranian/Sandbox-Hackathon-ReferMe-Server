package service;

import exception.SandboxServerErrorException;
import request.PostJobOpeningRequest;
import response.PostJobOpeningResponse;
import util.HTTPRegex;

import java.util.UUID;

public class PostJobOpeningService extends ServiceTemplate<PostJobOpeningRequest, PostJobOpeningResponse>{

    @Override
    public PostJobOpeningResponse doRequest(PostJobOpeningRequest request) {
        try {
            request.getJobOpening().setId(UUID.randomUUID().toString());
            getJobOpeningDAO().addJobOpening(request.getJobOpening());
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }
        return new PostJobOpeningResponse("Opening posted", true);
    }
}
