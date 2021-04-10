package service;

import exception.SandboxServerErrorException;
import model.Mentor;
import model.Pair;
import request.GetMentorsRequest;
import response.GetMentorsResponse;

import java.util.List;

public class GetMentorsService extends ServiceTemplate<GetMentorsRequest, GetMentorsResponse>{

    @Override
    public GetMentorsResponse doRequest(GetMentorsRequest request) {
        Pair<List<Mentor>, Boolean> outcome;

        try {
            outcome = getMentorDAO().getMentors(request.getLimit(), request.getLast(), request.getIndustry());
        } catch (SandboxServerErrorException e) {
            throw new SandboxServerErrorException(e.getMessage());
        } catch (Exception e) {
            throw new SandboxServerErrorException("[Server Error]: " + e.getMessage());
        }

        return new GetMentorsResponse(outcome.getFirst(), outcome.getSecond());
    }
}
