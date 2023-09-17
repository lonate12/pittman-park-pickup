package main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests;

import java.util.Objects;

public class GetAllGamesRequest {
    String userId;

    public GetAllGamesRequest() {
    }

    public GetAllGamesRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetAllGamesRequest)) return false;
        GetAllGamesRequest that = (GetAllGamesRequest) o;
        return Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        return "GetAllGamesRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
