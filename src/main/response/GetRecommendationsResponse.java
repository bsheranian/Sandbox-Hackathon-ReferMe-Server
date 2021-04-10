package response;
import model.Recommendation;

import java.util.List;

public class GetRecommendationsResponse {
    private List<Recommendation> recommendations;
    private boolean hasMorePages;

    public GetRecommendationsResponse() {}

    public GetRecommendationsResponse(List<Recommendation> recommendations, boolean hasMorePages) {
        this.recommendations = recommendations;
        this.hasMorePages = hasMorePages;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public boolean getHasMorePages() {
        return hasMorePages;
    }

    public void setHasMorePages(boolean hasMorePages) {
        this.hasMorePages = hasMorePages;
    }
}
