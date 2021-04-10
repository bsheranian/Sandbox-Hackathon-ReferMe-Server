package response;
import model.Recommendation;

public class getRecommendationsResponse {
    private List<Recommendation> recommendations;

    public getRecommendationsResponse() {}

    public getRecommendationsResponse(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}
