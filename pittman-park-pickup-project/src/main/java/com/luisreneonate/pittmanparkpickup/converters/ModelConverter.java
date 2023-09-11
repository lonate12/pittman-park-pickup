package main.java.com.luisreneonate.pittmanparkpickup.converters;

import main.java.com.luisreneonate.pittmanparkpickup.apimodels.GameModel;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.Game;

import javax.inject.Inject;

public class ModelConverter {
    @Inject
    public ModelConverter() {}

    /**
     * Convert a provided Game into a GameModel
     * @param game the game to convert
     * @return the converted game
     */
    public GameModel toGameModel(Game game) {
        // Note: Imagine here that we're getting game data from Dynamo and we want to do any kind of cleanup or conversion
        //  we may want to do here before sending the final GameModel along via API Gateway
        //  So, things we might want to do include formatting the LocalDateTime in a specific way, for example
        //  also, recall that DynamoDB return things as null if they're no in the DB, so this is where you could check for things
        //  like that and update fields in the model accordingly
        //  For our purposes, the Dynamo game model and the API gateway game model are the same, so we'll just
        //  pass along strings for everything

        return GameModel.builder()
                .withGameId(game.getGameId())
                .withGameTime(game.getGameTime())
                .withLocation(game.getLocation())
                .withPlayers(game.getPlayers())
                .withStatus(game.getStatus().toString())
                .build();
    }
}
