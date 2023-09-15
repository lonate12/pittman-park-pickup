package main.java.com.luisreneonate.pittmanparkpickup.apimodels.results;

import main.java.com.luisreneonate.pittmanparkpickup.apimodels.GameModel;

import java.util.Objects;

public class UpdateGameResult {

    private GameModel updatedGame;

    public UpdateGameResult() {
    }

    public UpdateGameResult(GameModel updatedGame) {
        this.updatedGame = updatedGame;
    }

    public GameModel getUpdatedGame() {
        return updatedGame;
    }

    public void setUpdatedGame(GameModel updatedGame) {
        this.updatedGame = updatedGame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateGameResult)) return false;
        UpdateGameResult that = (UpdateGameResult) o;
        return Objects.equals(getUpdatedGame(), that.getUpdatedGame());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUpdatedGame());
    }

    @Override
    public String toString() {
        return "UpdateGameResult{" +
                "updatedGame=" + updatedGame +
                '}';
    }
}
