package main.java.com.luisreneonate.pittmanparkpickup.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.GameModel;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.GetAllGamesRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.GetAllGamesResult;
import main.java.com.luisreneonate.pittmanparkpickup.converters.ModelConverter;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.GameDao;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.Game;
import main.java.com.luisreneonate.pittmanparkpickup.exceptions.NoActiveGamesException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetAllGamesActivity implements RequestHandler<GetAllGamesRequest, GetAllGamesResult> {
    private GameDao gameDao;
    private ModelConverter modelConverter;

    @Inject
    public GetAllGamesActivity(GameDao gameDao, ModelConverter modelConverter) {
        this.gameDao = gameDao;
        this.modelConverter = modelConverter;
    }

    @Override
    public GetAllGamesResult handleRequest(GetAllGamesRequest getAllGamesRequest, Context context) {
        System.out.println(getAllGamesRequest.getUserId());
        List<Game> allGames = gameDao.getAllUpcomingGames();
        if (allGames.isEmpty()) {
            throw new NoActiveGamesException("No active games scheduled right now.");
        }

        List<GameModel> gameModelList = modelConverter.toGameModelList(allGames);

        return new GetAllGamesResult(gameModelList);
    }
}
