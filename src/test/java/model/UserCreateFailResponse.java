package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class UserCreateFailResponse {

        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("message")
        @Expose
        private List<String> message;

        public UserCreateFailResponse() {
        }

        public UserCreateFailResponse(Boolean success, List<String> message) {
            super();
            this.success = success;
            this.message = message;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public List<String> getMessage() {
            return message;
        }

        public void setMessage(List<String> message) {
            this.message = message;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCreateFailResponse that)) return false;
        return Objects.equals(getSuccess(), that.getSuccess()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSuccess(), getMessage());
    }

    @Override
    public String toString() {
        return "UserCreateFailResponse{" +
                "success=" + success +
                ", message=" + message +
                '}';
    }
}
