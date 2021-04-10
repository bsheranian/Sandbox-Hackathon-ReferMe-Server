package service;

import dao.MentorDAO;
import dao.StudentDAO;
import exception.SandboxServerErrorException;
import model.Mentor;
import model.Pair;
import model.Student;
import request.getMyStudentsRequest;
import response.getMyMentorsResponse;
import response.getMyStudentsResponse;
import util.HTTPRegex;

import java.util.ArrayList;
import java.util.List;

public class GetMyStudentsService extends ServiceTemplate<getMyStudentsRequest, getMyStudentsResponse>{

    @Override
    public getMyStudentsResponse doRequest(getMyStudentsRequest request) {
        boolean hasMorePages;
        List<Student> students = new ArrayList<>();

        try {
            String mentorId = getAuthDAO().getUsername(request.getToken());
            Pair<List<String>, Boolean> response = getMatchDAO().getStudents(request.getLimit(), request.getLast(), mentorId);
            hasMorePages = response.getSecond();
            StudentDAO studentDAO = getStudentDAO();

            for (String id : response.getFirst()) {
                System.out.print(id + " ");
            }
            for (String id : response.getFirst()) {
                students.add(studentDAO.getStudent(id, request.getIndustry()));
            }
        } catch (Exception e) {
            throw new SandboxServerErrorException(HTTPRegex.SERVER_ERROR);
        }

        System.out.println("hasMorePages: " + hasMorePages);

        return new getMyStudentsResponse(students, hasMorePages);
    }

}
