package service;

import exception.SandboxServerErrorException;
import model.Match;
import request.RequestMatchRequest;
import response.RequestMatchResponse;
import util.HTTPRegex;
import util.UserType;

public class RequestMatchService extends ServiceTemplate<RequestMatchRequest, RequestMatchResponse>{
    @Override
    public RequestMatchResponse doRequest(RequestMatchRequest request) {
        try {

            String username = getAuthDAO().getUsername(request.getToken());
            int userType = getCredDAO().getUserType(username);
            if (userType == UserType.STUDENT) {
                Match newMatch = new Match(username, request.getRequestedUserId(), username);
                getMatchDAO().addMatch(newMatch);
            } else {
                Match newMatch = new Match(request.getRequestedUserId(), username, username);
                getMatchDAO().addMatch(newMatch);
            }

        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new RequestMatchResponse("Match requested", true);
    }
}
