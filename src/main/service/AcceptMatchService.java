package service;

import exception.SandboxServerErrorException;
import request.AcceptMatchRequest;
import response.acceptMatchResponse;
import util.HTTPRegex;

public class AcceptMatchService extends ServiceTemplate<AcceptMatchRequest, acceptMatchResponse>{


    @Override
    public acceptMatchResponse doRequest(AcceptMatchRequest request) {
        try {
            getMatchDAO().acceptMatch(request.getStudentId(), request.getMentorId());
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new acceptMatchResponse("Match accepted", true);
    }
}
