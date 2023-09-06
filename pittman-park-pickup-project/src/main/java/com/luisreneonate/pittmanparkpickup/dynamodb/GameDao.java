package main.java.com.luisreneonate.pittmanparkpickup.dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.Game;
import main.java.com.luisreneonate.pittmanparkpickup.exceptions.GameNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Accesses data from a game using {@link Game} to represent the model in DynamoDB.
 */
@Singleton
public class GameDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public GameDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Game saveGame(Game game) {
        this.dynamoDBMapper.save(game);
        return game;
    }

    public Game getGame (String gameId) {
        Game game = this.dynamoDBMapper.load(Game.class, gameId);

        if (game == null) {
            throw new GameNotFoundException("Could not find a game with id " + gameId);
        }

        return game;
    }

    public List<Game> getAllUpcomingGames () {
        // Question: Not sure how I'd get all the game from DynamoDB
        //  Would I use a dynamoDBMapper.query()?
        // Note: Yep, need to use .query(), get with Swastik once I'm at this point.
        return new ArrayList<>();
    }
}
