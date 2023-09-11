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

        // We won't be getting a gameID, we'll generate that when creating the game
        // We'll get a gameTime, we need to check to make sure the gameTime is after the current localdatetime
        // Not much validation on the game location. Because it's specific to pittman park, we'll hardcode pittman as the location
        // Since this is a creation request, we'll assume that the admin creating the game is going to RSVP, so the
        //  players list will contain the creating admin, by default
        // The status will be set to Active automatically
        // So, the only thing we need from the FE is just the localdatetime

        // First, let's check to see if the user making the create game request knows the password...
        if (!createGameRequest.getPw().equals("SuperSecretPW1")) {
            throw new InvalidGameAttributeException("Looks like you're not authorised to create a game. Please ask an admin to create the game.");
        }

        User requestingUser = userDao.getUser(createGameRequest.getUserId());
        if (requestingUser == null || !requestingUser.getRole().equalsIgnoreCase("admin")) {
            throw new InvalidGameAttributeException("The requesting user cannot create a game. Please check with an admin to see what the problem is.");
        }

        // This is where we'd do our validation checks
        LocalDateTime proposedDateTime = LocalDateTime.parse(createGameRequest.getGameTime());
        LocalDateTime now = LocalDateTime.now();
        if (!proposedDateTime.isAfter(now)) {
            throw new InvalidGameAttributeException("The date and time for the game cannot be in the past.");
        }

        List<User> playerList = new ArrayList<>();
        playerList.add(requestingUser);

        // Create game object and save to the DB
        Game game = new Game(proposedDateTime.toString(), playerList);
        Game savedGame = gameDao.saveGame(game);

        // Construct new GameModel to return the response with
        GameModel newGameModel = modelConverter.toGameModel(savedGame);
        return CreateGameResult.builder().withGame(newGameModel).build();
    }
}
