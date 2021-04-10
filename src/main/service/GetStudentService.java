package service;

import exception.SandboxServerErrorException;
import model.Student;
import request.GetStudentRequest;
import response.GetStudentResponse;

public class GetStudentService extends ServiceTemplate<GetStudentRequest, GetStudentResponse>{
    @Override
    public GetStudentResponse doRequest(GetStudentRequest request) {
        Student newStudent;
        try {
            newStudent = getStudentDAO().getStudent(request.getStudentId(), request.getIndustry());
        } catch (Exception e) {
            throw new SandboxServerErrorException("[Server Error]: " + e.getMessage());
        }

        return new GetStudentResponse(newStudent);
    }
}
