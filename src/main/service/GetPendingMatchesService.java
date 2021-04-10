package service;

import exception.SandboxServerErrorException;
import model.Pair;
import model.User;
import request.GetPendingMatchesRequest;
import response.GetPendingMatchesResponse;
import util.HTTPRegex;
import util.UserType;

import java.util.ArrayList;
import java.util.List;

public class GetPendingMatchesService extends ServiceTemplate<GetPendingMatchesRequest, GetPendingMatchesResponse>{
    @Override
    public GetPendingMatchesResponse doRequest(GetPendingMatchesRequest request) {
        boolean hasMorePages;
        List<User> pendingRequests = new ArrayList<>();

        try {
            String username = getAuthDAO().getUsername(request.getToken());
            int userType = getCredDAO().getUserType(username);

            Pair<List<String>, Boolean> outcome = getMatchDAO().getMentors(request.getLimit(), request.getLast(), username);
            hasMorePages = outcome.getSecond();

            for (String id : outcome.getFirst()) {
                if (userType == UserType.STUDENT) {
                    pendingRequests.add(getMentorDAO().getMentor(id, request.getIndustry()));
                } else {
                    pendingRequests.add(getStudentDAO().getStudent(id, request.getIndustry()));
                }
            }
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage());
        }

        return new GetPendingMatchesResponse(pendingRequests, hasMorePages);
    }
}
