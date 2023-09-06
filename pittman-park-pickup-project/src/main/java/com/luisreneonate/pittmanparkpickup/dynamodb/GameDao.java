package main.java.com.luisreneonate.pittmanparkpickup.dynamodb;


import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.Game;
import main.java.com.luisreneonate.pittmanparkpickup.exceptions.GameNotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * Accesses data from a game using {@link Game} to represent the model in DynamoDB.
 */
@Singleton
public class GameDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public GameDao(DynamoDBMapper dynamoDBMapper) {
        // Question: If we're using @Inject here, I don't think we need the line below, is that right?
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Game saveGame(Game game) {
        this.dynamoDBMapper.save(game);
        // Question: How do we know if the mapper was able to save the game successfully?
        return game;
    }

    public Game getGame (String gameId) {
        // Question: Should I be passing in the gameId as a UUID and then converting to a string?
        //  What's the best practice when you know you have one data type, but need to convert to a different
        //  datatype for purposes like passing to a mapper object? Should we just keep it as the end data type
        //  we'd need or try to keep it as the original data type until we need to convert it in order to use it
        //  for the mapper?
        Game game = this.dynamoDBMapper.load(Game.class, gameId);

        if (game == null) {
            throw new GameNotFoundException("Could not find a game with id " + gameId);
        }

        return game;
    }

    public List<Game> getAllUpcomingGames () {
        // Question: Not sure how I'd get all the game from DynamoDB
        //  Would I use a dynamoDBMapper.query()?
    }
}
