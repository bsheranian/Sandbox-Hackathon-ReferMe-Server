package service;

import exception.SandboxServerErrorException;
import model.Mentor;
import model.Student;
import request.getStudentRequest;
import response.getMentorResponse;
import response.getStudentResponse;

public class GetStudentService extends ServiceTemplate<getStudentRequest, getStudentResponse>{
    @Override
    public getStudentResponse doRequest(getStudentRequest request) {
        Student newStudent;
        try {
            newStudent = getStudentDAO().getStudent(request.getStudentId(), request.getIndustry());
        } catch (Exception e) {
            throw new SandboxServerErrorException("[Server Error]: " + e.getMessage());
        }

        return new getStudentResponse(newStudent);
    }
}
