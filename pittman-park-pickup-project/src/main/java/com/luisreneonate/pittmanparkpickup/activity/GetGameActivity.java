package main.java.com.luisreneonate.pittmanparkpickup.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.GameModel;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.GetGameRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.GetGameResult;
import main.java.com.luisreneonate.pittmanparkpickup.converters.ModelConverter;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.GameDao;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.Game;

import javax.inject.Inject;

public class GetGameActivity implements RequestHandler<GetGameRequest, GetGameResult> {
    private final GameDao gameDao;

    @Inject
    public GetGameActivity(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public GetGameResult handleRequest(final GetGameRequest getGameRequest, Context context) {
        // Get the game ID from the request
        String gameId = getGameRequest.getGameId();
        Game game = gameDao.getGame(gameId);
        GameModel gameModel = new ModelConverter().toGameModel(game);

        return GetGameResult.builder().withGame(gameModel).build();
    }
}
