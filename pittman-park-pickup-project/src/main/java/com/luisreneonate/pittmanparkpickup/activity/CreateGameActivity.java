package main.java.com.luisreneonate.pittmanparkpickup.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.GameModel;
import main.java.com.luisreneonate.pittmanparkpickup.converters.ModelConverter;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.GameDao;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.UserDao;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.Game;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.CreateGameRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.CreateGameResult;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.User;
import main.java.com.luisreneonate.pittmanparkpickup.exceptions.InvalidGameAttributeException;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateGameActivity implements RequestHandler<CreateGameRequest, CreateGameResult> {
    private final GameDao gameDao;
    private final UserDao userDao;
    private ModelConverter modelConverter;

    @Inject
    public CreateGameActivity(GameDao gameDao, UserDao userDao, ModelConverter modelConverter) {
        this.gameDao = gameDao;
        this.userDao = userDao;
        this.modelConverter = modelConverter;
    }

    @Override
    public CreateGameResult handleRequest(CreateGameRequest createGameRequest, Context context) {
        System.out.println("Received request to create new game");

        // First, let's check to see if the user making the create game request knows the password...
        if (!createGameRequest.getPw().equals("SuperSecretPW1")) {
            throw new InvalidGameAttributeException("Looks like you're not authorised to create a game. Please ask an admin to create the game.");
        }

        User requestingUser = userDao.getUser(createGameRequest.getUserId());
        if (requestingUser == null || !requestingUser.getRole().equalsIgnoreCase("admin")) {
            throw new InvalidGameAttributeException("You don't have permissions to create a game. Please check with an admin to see what the problem is.");
        }

        // This is where we'd do our validation checks
        long proposedDateTime = Long.parseLong(createGameRequest.getGameTime());
        long currentTimeInMillis = System.currentTimeMillis();
        if (proposedDateTime < currentTimeInMillis) {
            throw new InvalidGameAttributeException("The date and time for the game cannot be in the past.");
        }

        List<User> playerList = new ArrayList<>();
        playerList.add(requestingUser);

        // Create game object and save to the DB
        Game game = new Game(createGameRequest.getGameTime(), playerList);
        Game savedGame = gameDao.saveGame(game);

        // Construct new GameModel to return the response with
        GameModel newGameModel = modelConverter.toGameModel(savedGame);
        return CreateGameResult.builder().withGame(newGameModel).build();
    }
}
