package main.java.com.luisreneonate.pittmanparkpickup.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.GameDao;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.Game;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.CreateGameRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.CreateGameResult;

import javax.inject.Inject;

public class CreateGameActivity implements RequestHandler<CreateGameRequest, CreateGameResult> {
    private final GameDao gameDao;

    @Inject
    public CreateGameActivity(GameDao gameDao) {
        this.gameDao = gameDao;
    }
    @Override
    public CreateGameResult handleRequest(CreateGameRequest createGameRequest, Context context) {
        System.out.println("Received request to create new game");

        // This is where we'd do our validation checks
        // TODO: Incorporate validation checks, for now, we'll assume the request is a valid one.

        // Create game object that we'll later save to the DB
        Game game = new Game();
        return null;
    }
}
