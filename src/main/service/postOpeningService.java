package service;

import exception.SandboxServerErrorException;
import request.PostOpeningRequest;
import response.postOpeningResponse;
import util.HTTPRegex;

public class postOpeningService extends ServiceTemplate<PostOpeningRequest, postOpeningResponse>{

    @Override
    public postOpeningResponse doRequest(PostOpeningRequest request) {
        try {
            getJobOpeningDAO().addJobOpening(request.getJobOpening());
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }
        return new postOpeningResponse("Opening posted", true);
    }
}
