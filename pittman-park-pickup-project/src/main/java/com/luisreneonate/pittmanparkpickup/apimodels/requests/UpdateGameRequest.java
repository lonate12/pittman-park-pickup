package main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests;

import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.User;

import java.util.List;
import java.util.Objects;

public class UpdateGameRequest {
    private String gameId;
    private String gameTime;
    private String location;
    private String userId;
    private String pw;
    private String status;
    private RSVPRequest playerRsvp;
    private List<User> players;

    public UpdateGameRequest() {
    }

    public UpdateGameRequest(Builder builder) {
        this.gameId = builder.gameId;
        this.gameTime = builder.gameTime;
        this.location = builder.location;
        this.userId = builder.userId;
        this.pw = builder.pw;
        this.status = builder.status;
        this.playerRsvp = builder.playerRsvp;
        this.players = builder.players;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RSVPRequest getPlayerRsvp() {
        return playerRsvp;
    }

    public void setPlayerRsvp(RSVPRequest playerRsvp) {
        this.playerRsvp = playerRsvp;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateGameRequest)) return false;
        UpdateGameRequest that = (UpdateGameRequest) o;
        return Objects.equals(getGameId(), that.getGameId()) && Objects.equals(getGameTime(), that.getGameTime()) && Objects.equals(getLocation(), that.getLocation()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getPw(), that.getPw()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getPlayerRsvp(), that.getPlayerRsvp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getGameTime(), getLocation(), getUserId(), getPw(), getStatus(), getPlayerRsvp());
    }

    @Override
    public String toString() {
        return "UpdateGameRequest{" +
                "gameId='" + gameId + '\'' +
                ", gameTime='" + gameTime + '\'' +
                ", location='" + location + '\'' +
                ", userId='" + userId + '\'' +
                ", pw='" + pw + '\'' +
                ", status='" + status + '\'' +
                ", playerRsvp=" + playerRsvp +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String gameId;
        private String gameTime;
        private String location;
        private String userId;
        private String pw;
        private String status;
        private RSVPRequest playerRsvp;
        private List<User> players;

        private Builder() {
        }

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

        public Builder withUserId(String userIdToUse) {
            this.userId = userIdToUse;
            return this;
        }

        public Builder withPw(String pwToUse) {
            this.pw = pwToUse;
            return this;
        }

        public Builder withStatus(String statusToUse) {
            this.status = statusToUse;
            return this;
        }

        public Builder withPlayerRsvp(RSVPRequest rsvpToUse) {
            this.playerRsvp = rsvpToUse;
            return this;
        }

        public Builder withPlayers(List<User> playersToUse) {
            this.players = playersToUse;
            return this;
        }

        public UpdateGameRequest build() {
            return new UpdateGameRequest(this);
        }
    }
}
