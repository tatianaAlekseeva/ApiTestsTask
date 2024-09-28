package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class SavedUserData {
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("id")
        @Expose
        private Integer id;

        public SavedUserData() {
        }

        public SavedUserData(String username, String email, String password, String createdAt, String updatedAt, Integer id) {
            super();
            this.username = username;
            this.email = email;
            this.password = password;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SavedUserData savedUserData)) return false;
        return Objects.equals(getUsername(), savedUserData.getUsername()) && Objects.equals(getEmail(), savedUserData.getEmail()) && Objects.equals(getPassword(), savedUserData.getPassword()) && Objects.equals(getCreatedAt(), savedUserData.getCreatedAt()) && Objects.equals(getUpdatedAt(), savedUserData.getUpdatedAt()) && Objects.equals(getId(), savedUserData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail(), getPassword(), getCreatedAt(), getUpdatedAt(), getId());
    }

    @Override
    public String toString() {
        return "Details{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", id=" + id +
                '}';
    }
}
