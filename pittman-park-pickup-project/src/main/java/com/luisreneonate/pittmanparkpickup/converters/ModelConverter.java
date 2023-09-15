package main.java.com.luisreneonate.pittmanparkpickup.converters;

import main.java.com.luisreneonate.pittmanparkpickup.apimodels.GameModel;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.UserModel;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.Game;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    @Inject
    public ModelConverter() {}

    /**
     * Convert a provided Game into a GameModel
     * @param game the game to convert
     * @return the converted game
     */
    public GameModel toGameModel(Game game) {
        List<UserModel> userModelList = new ArrayList<>();
        for (User user : game.getPlayers()) {
            userModelList.add(toUserModel(user));
        }
        return GameModel.builder()
                .withGameId(game.getGameId())
                .withGameTime(game.getGameTime())
                .withLocation(game.getLocation())
                .withPlayers(userModelList)
                .withStatus(game.getStatus().toString())
                .build();
    }

    public UserModel toUserModel(User user) {
        return UserModel.builder()
                .withUserId(user.getUserId())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .build();
    }
}
