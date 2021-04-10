package service;

import exception.SandboxServerErrorException;
import model.Mentor;
import model.Pair;
import model.Student;
import request.getStudentsRequest;
import response.getMentorsResponse;
import response.getStudentsResponse;

import java.util.List;

public class GetStudentsService extends ServiceTemplate<getStudentsRequest, getStudentsResponse> {

    @Override
    public getStudentsResponse doRequest(getStudentsRequest request) {
        Pair<List<Student>, Boolean> outcome;

        try {
            outcome = getStudentDAO().getStudents(request.getLimit(), request.getLast(), request.getIndustry());
        } catch (SandboxServerErrorException e) {
            throw new SandboxServerErrorException(e.getMessage());
        } catch (Exception e) {
            throw new SandboxServerErrorException("[Server Error]: " + e.getMessage());
        }

        return new getStudentsResponse(outcome.getFirst(), outcome.getSecond());
    }
}
