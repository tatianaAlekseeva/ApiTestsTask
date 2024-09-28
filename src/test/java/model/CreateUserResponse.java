package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class CreateUserResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("details")
    @Expose
    private SavedUserData savedUserData;
    @SerializedName("message")
    @Expose
    private String message;

    public CreateUserResponse() {
    }

    public CreateUserResponse(Boolean success, SavedUserData savedUserData, String message) {
        super();
        this.success = success;
        this.savedUserData = savedUserData;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public SavedUserData getDetails() {
        return savedUserData;
    }

    public void setDetails(SavedUserData savedUserData) {
        this.savedUserData = savedUserData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateUserResponse that)) return false;
        return Objects.equals(getSuccess(), that.getSuccess()) && Objects.equals(getDetails(), that.getDetails()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSuccess(), getDetails(), getMessage());
    }

    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "success=" + success +
                ", details=" + savedUserData +
                ", message='" + message + '\'' +
                '}';
    }
}
