package service;

import exception.SandboxServerErrorException;
import request.DeclineMatchRequest;
import response.DeclineMatchResponse;
import util.HTTPRegex;

public class DeclineMatchService extends ServiceTemplate<DeclineMatchRequest, DeclineMatchResponse>{
    @Override
    public DeclineMatchResponse doRequest(DeclineMatchRequest request) {
        try {
            getMatchDAO().deleteMatch(request.getStudentId(), request.getMentorId());
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new DeclineMatchResponse("Match declined", true);
    }
}

