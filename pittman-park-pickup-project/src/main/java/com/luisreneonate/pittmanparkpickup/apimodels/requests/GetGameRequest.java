package main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests;

import java.util.Objects;

public class GetGameRequest {
    private String gameId;

    public GetGameRequest() {
    }

    public GetGameRequest(Builder builder) {
        this.gameId = builder.gameId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetGameRequest)) return false;
        GetGameRequest that = (GetGameRequest) o;
        return Objects.equals(getGameId(), that.getGameId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId());
    }

    @Override
    public String toString() {
        return "GetGameRequest{" +
                "gameId='" + gameId + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String gameId;

        private Builder() {}

        public Builder withGameId(String gameIdToUse) {
            this.gameId = gameIdToUse;
            return this;
        }

        public GetGameRequest build() {
            return new GetGameRequest(this);
        }
    }
}
