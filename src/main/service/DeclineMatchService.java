package service;

import exception.SandboxServerErrorException;
import request.declineMatchRequest;
import response.declineMatchResponse;
import util.HTTPRegex;

public class DeclineMatchService extends ServiceTemplate<declineMatchRequest, declineMatchResponse>{
    @Override
    public declineMatchResponse doRequest(declineMatchRequest request) {
        try {
            getMatchDAO().deleteMatch(request.getStudentId(), request.getMentorId());
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new declineMatchResponse("Match declined", true);
    }
}

