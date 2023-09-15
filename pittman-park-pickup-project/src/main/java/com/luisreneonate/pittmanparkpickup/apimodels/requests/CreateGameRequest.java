package main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests;

import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.User;

import java.util.List;
import java.util.Objects;

public class CreateGameRequest {
    String gameId;
    String gameTime;
    String location;
    List<User> players;
    String status;
    String pw;
    String userId;

    public CreateGameRequest() {}

    public CreateGameRequest(Builder builder) {
        this.gameId = builder.gameId;
        this.gameTime = builder.gameTime;
        this.location = builder.location;
        this.players = builder.players;
        this.status = builder.status;
        this.pw = builder.pw;
        this.userId = builder.userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPw() {return pw; }
    public void setPw(String pw) { this.pw = pw; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateGameRequest)) return false;
        CreateGameRequest that = (CreateGameRequest) o;
        return Objects.equals(getGameId(), that.getGameId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId());
    }

    @Override
    public String toString() {
        return "CreateGameRequest{" +
                "gameId='" + gameId + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        String gameId;
        String gameTime;
        String location;
        List<User> players;
        String status;
        String pw;
        String userId;

        private Builder() {}

        public Builder withGameId(String gameIdToUse) {
            this.gameId = gameIdToUse;
            return this;
        }

        public Builder withGameTime(String gameTimeToUse) {
            this.gameTime = gameTimeToUse;
            return this;
        }

        public Builder withLocation(String locationToUse) {
            this.location = locationToUse;
            return this;
        }

        public Builder withPlayers(List<User> playersToUse) {
            this.players = playersToUse;
            return this;
        }

        public Builder withStatus(String statusToUse) {
            this.status = statusToUse;
            return this;
        }

        public Builder withPw(String pwToUse) {
            this.pw = pwToUse;
            return this;
        }

        public Builder withUserId(String userIdToUse) {
            this.userId = userIdToUse;
            return this;
        }

        public CreateGameRequest build() {
            return new CreateGameRequest(this);
        }
    }
}
