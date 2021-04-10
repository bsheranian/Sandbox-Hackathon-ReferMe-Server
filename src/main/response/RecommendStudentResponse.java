package response;

public class RecommendStudentResponse {
    private String message;
    private Boolean success;

    public RecommendStudentResponse(){}

    public RecommendStudentResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
