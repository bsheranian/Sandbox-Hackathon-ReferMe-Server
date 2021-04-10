package service;

import exception.SandboxServerErrorException;
import model.Pair;
import model.Student;
import request.GetStudentsRequest;
import response.GetStudentsResponse;

import java.util.List;

public class GetStudentsService extends ServiceTemplate<GetStudentsRequest, GetStudentsResponse> {

    @Override
    public GetStudentsResponse doRequest(GetStudentsRequest request) {
        Pair<List<Student>, Boolean> outcome;

        try {
            outcome = getStudentDAO().getStudents(request.getLimit(), request.getLast(), request.getIndustry());
        } catch (SandboxServerErrorException e) {
            throw new SandboxServerErrorException(e.getMessage());
        } catch (Exception e) {
            throw new SandboxServerErrorException("[Server Error]: " + e.getMessage());
        }

        return new GetStudentsResponse(outcome.getFirst(), outcome.getSecond());
    }
}
