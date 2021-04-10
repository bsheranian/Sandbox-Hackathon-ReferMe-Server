package service;

import exception.SandboxServerErrorException;
import model.Pair;
import model.User;
import request.GetPendingMatchesRequest;
import response.GetPendingMatchesResponse;
import util.HTTPRegex;
import util.StackTraceUtils;
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


            if (userType == UserType.STUDENT) {
                Pair<List<String>, Boolean> outcome = getMatchDAO().getPendingMentors(request.getLimit(), request.getLast(), username);
                hasMorePages = outcome.getSecond();
                for (String id : outcome.getFirst()) {
                    pendingRequests.add(getMentorDAO().getMentor(id, request.getIndustry()));
                }
            } else {
                Pair<List<String>, Boolean> outcome = getMatchDAO().getPendingStudents(request.getLimit(), request.getLast(), username);
                hasMorePages = outcome.getSecond();
                for (String id : outcome.getFirst()) {
                    pendingRequests.add(getStudentDAO().getStudent(id, request.getIndustry()));
                }
            }

        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR + e.getMessage() + StackTraceUtils.traceToString(e.getStackTrace()));
        }

        return new GetPendingMatchesResponse(pendingRequests, hasMorePages);
    }
}
