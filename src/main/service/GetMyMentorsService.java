package service;

import dao.MentorDAO;
import exception.SandboxServerErrorException;
import model.Mentor;
import model.Pair;
import request.GetMyMentorsRequest;
import response.GetMyMentorsResponse;
import util.HTTPRegex;

import java.util.ArrayList;
import java.util.List;

public class GetMyMentorsService extends ServiceTemplate<GetMyMentorsRequest, GetMyMentorsResponse>{
    @Override
    public GetMyMentorsResponse doRequest(GetMyMentorsRequest request) {

        boolean hasMorePages;
        List<Mentor> mentors = new ArrayList<>();

        try {
            String studentId = getAuthDAO().getUsername(request.getToken());
            Pair<List<String>, Boolean> response = getMatchDAO().getMentors(request.getLimit(), request.getLast(), studentId);
            hasMorePages = response.getSecond();
            MentorDAO mentorDAO = getMentorDAO();

            for (String id : response.getFirst()) {
                System.out.print(id + " ");
            }
            for (String id : response.getFirst()) {
                mentors.add(mentorDAO.getMentor(id, request.getIndustry()));
            }
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR);
        }

        System.out.println("hasMorePages: " + hasMorePages);

        return new GetMyMentorsResponse(mentors, hasMorePages);
    }
}
