package main.java.com.luisreneonate.pittmanparkpickup.apimodels.results;

import main.java.com.luisreneonate.pittmanparkpickup.apimodels.GameModel;

import java.util.List;
import java.util.Objects;

public class GetAllGamesResult {
    private List<GameModel> games;

    public GetAllGamesResult() {
    }

    public GetAllGamesResult(List<GameModel> games) {
        this.games = games;
    }

    public List<GameModel> getGames() {
        return games;
    }

    public void setGames(List<GameModel> games) {
        this.games = games;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetAllGamesResult)) return false;
        GetAllGamesResult that = (GetAllGamesResult) o;
        return Objects.equals(getGames(), that.getGames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGames());
    }

    @Override
    public String toString() {
        return "GetAllGamesResult{" +
                "games=" + games +
                '}';
    }
}
