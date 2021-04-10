package service;

import exception.SandboxServerErrorException;
import model.Mentor;
import request.getMentorRequest;
import response.getMentorResponse;

public class GetMentorService extends ServiceTemplate<getMentorRequest, getMentorResponse>{
    @Override
    public getMentorResponse doRequest(getMentorRequest request) {
        Mentor newMentor;
        try {
            newMentor = getMentorDAO().getMentor(request.getMentorId(), request.getIndustry());
        } catch (Exception e) {
            throw new SandboxServerErrorException("[Server Error]: " + e.getMessage());
        }

        return new getMentorResponse(newMentor);
    }
}
