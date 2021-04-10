package service;

import exception.SandboxServerErrorException;
import model.Mentor;
import request.GetMentorRequest;
import response.GetMentorResponse;

public class GetMentorService extends ServiceTemplate<GetMentorRequest, GetMentorResponse>{
    @Override
    public GetMentorResponse doRequest(GetMentorRequest request) {
        Mentor newMentor;
        try {
            newMentor = getMentorDAO().getMentor(request.getMentorId(), request.getIndustry());
        } catch (Exception e) {
            throw new SandboxServerErrorException("[Server Error]: " + e.getMessage());
        }

        return new GetMentorResponse(newMentor);
    }
}
