package main.java.com.luisreneonate.pittmanparkpickup.apimodels.results;

import main.java.com.luisreneonate.pittmanparkpickup.apimodels.GameModel;

import java.util.Locale;

public class GetGameResult {
    private GameModel game;

    public GetGameResult(Builder builder) {
        this.game = builder.game;
    }

    public GameModel getGame() {
        return game;
    }

    public void setGame(GameModel game) {
        this.game = game;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private GameModel game;

        public Builder withGame(GameModel gameToUse) {
            this.game = gameToUse;
            return this;
        }

        public GetGameResult build() {
            return new GetGameResult(this);
        }
    }
}
