package service;

import exception.SandboxLoginException;
import exception.SandboxServerErrorException;
import model.Mentor;
import model.Pair;
import request.getMentorsRequest;
import response.getMentorsResponse;

import java.util.List;

public class GetMentorsService extends ServiceTemplate<getMentorsRequest, getMentorsResponse>{

    @Override
    public getMentorsResponse doRequest(getMentorsRequest request) {
        Pair<List<Mentor>, Boolean> outcome;

        try {
            outcome = getMentorDAO().getMentors(request.getLimit(), request.getLast(), request.getIndustry());
        } catch (SandboxServerErrorException e) {
            throw new SandboxServerErrorException(e.getMessage());
        } catch (Exception e) {
            throw new SandboxServerErrorException("[Server Error]: " + e.getMessage());
        }

        return new getMentorsResponse(outcome.getFirst(), outcome.getSecond());
    }
}
