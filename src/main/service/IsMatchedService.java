package service;

import exception.SandboxServerErrorException;
import request.IsMatchedRequest;
import response.IsMatchedResponse;
import util.HTTPRegex;
import util.UserType;

public class IsMatchedService extends ServiceTemplate<IsMatchedRequest, IsMatchedResponse> {

    @Override
    public IsMatchedResponse doRequest(IsMatchedRequest request) {

        boolean areMatched;
        boolean userRequestedMatch;
        boolean otherUserRequestedMatch;

        try {
            String username = getAuthDAO().getUsername(request.getToken());
            int userType = getCredDAO().getUserType(username);
            if (userType == UserType.STUDENT) {
                areMatched = getMatchDAO().areMatched(username, request.getRequestedUserId());
                userRequestedMatch = getMatchDAO().userRequestedMatch(username, request.getRequestedUserId(), true);
                otherUserRequestedMatch = getMatchDAO().userRequestedMatch(username, request.getRequestedUserId(), false);
            } else {
                areMatched = getMatchDAO().areMatched(request.getRequestedUserId(), username);
                userRequestedMatch = getMatchDAO().userRequestedMatch(username, request.getRequestedUserId(), false);
                otherUserRequestedMatch = getMatchDAO().userRequestedMatch(username, request.getRequestedUserId(), true);
            }

        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new IsMatchedResponse(areMatched, userRequestedMatch, otherUserRequestedMatch);
    }
}
