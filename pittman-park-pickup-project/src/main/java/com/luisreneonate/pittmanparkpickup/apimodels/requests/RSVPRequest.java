package main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests;

import java.util.Objects;

public class RSVPRequest {
    private String action;
    private String playerId;

    public RSVPRequest() {
    }

    public RSVPRequest(String action, String playerId) {
        this.action = action;
        this.playerId = playerId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RSVPRequest)) return false;
        RSVPRequest rsvp = (RSVPRequest) o;
        return Objects.equals(getAction(), rsvp.getAction()) && Objects.equals(getPlayerId(), rsvp.getPlayerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAction(), getPlayerId());
    }

    @Override
    public String toString() {
        return "RSVP{" +
                "action='" + action + '\'' +
                ", playerId='" + playerId + '\'' +
                '}';
    }
}
