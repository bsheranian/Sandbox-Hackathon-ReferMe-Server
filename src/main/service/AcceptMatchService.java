package service;

import exception.SandboxServerErrorException;
import request.AcceptMatchRequest;
import response.AcceptMatchResponse;
import util.HTTPRegex;

public class AcceptMatchService extends ServiceTemplate<AcceptMatchRequest, AcceptMatchResponse>{


    @Override
    public AcceptMatchResponse doRequest(AcceptMatchRequest request) {
        try {
            getMatchDAO().acceptMatch(request.getStudentId(), request.getMentorId());
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new AcceptMatchResponse("Match accepted", true);
    }
}
