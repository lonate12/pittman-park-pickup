package main.java.com.luisreneonate.pittmanparkpickup.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.RSVPRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.UpdateGameRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.UpdateGameResult;
import main.java.com.luisreneonate.pittmanparkpickup.converters.ModelConverter;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.GameDao;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.UserDao;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.Game;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.GameStatus;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.User;
import main.java.com.luisreneonate.pittmanparkpickup.exceptions.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UpdateGameActivity implements RequestHandler<UpdateGameRequest, UpdateGameResult> {
    private final GameDao gameDao;
    private final UserDao userDao;
    private final ModelConverter modelConverter;

    @Inject
    public UpdateGameActivity (GameDao gameDao, UserDao userDao, ModelConverter modelConverter) {
        this.gameDao = gameDao;
        this.userDao = userDao;
        this.modelConverter = modelConverter;
    }
    @Override
    public UpdateGameResult handleRequest(UpdateGameRequest updateGameRequest, Context context) {
        System.out.println("Received request to update existing game");
        // First, let's see if we can find the game and, if so, check to see if it's a game that hasn't been played yet
        //  if it has been played, we won't let any updated happen since it wouldn't make sense.
        Game gameToUpdate = gameDao.getGame(updateGameRequest.getGameId());
        if (gameToUpdate == null) {
            throw new GameNotFoundException("Could not requested game to update");
        }
        if (gameToUpdate.getStatus() == GameStatus.PLAYED) {
            throw new GameAlreadyPlayedException("Cannot update this game, the game has already happened");
        }

        // RSVP update process
        if (updateGameRequest.getPlayerRsvp() != null) {
            User rsvpPlayer = userDao.getUser(updateGameRequest.getPlayerRsvp().getPlayerId());
            String rsvpAction = updateGameRequest.getPlayerRsvp().getAction();
            List<User> gameRsvpList = gameToUpdate.getPlayers();

            if (rsvpAction.equalsIgnoreCase("add")) {
                if (!gameRsvpList.contains(rsvpPlayer)) {
                    gameRsvpList.add(rsvpPlayer);
                    System.out.println("Added " + rsvpPlayer.getEmail() + " to game.");
                    gameDao.saveGame(gameToUpdate);
                    return new UpdateGameResult(modelConverter.toGameModel(gameToUpdate));
                } else {
                    throw new InvalidGameAttributeException("Can't add a player to a game that's already RSVP'd.");
                }
            }

            if (rsvpAction.equalsIgnoreCase("remove")) {
                if (gameRsvpList.contains(rsvpPlayer)) {
                    gameRsvpList.remove(rsvpPlayer);
                    System.out.println("Removed " + rsvpPlayer.getEmail() + " from game.");
                    gameDao.saveGame(gameToUpdate);
                    return new UpdateGameResult(modelConverter.toGameModel(gameToUpdate));
                } else {
                    throw new InvalidGameAttributeException("Can't remove a player from a game that hasn't RSVP'd.");
                }
            }
        } else {
            // Game Update Process
            // First, let's check if the user is allowed to request the change
            if (updateGameRequest.getPw() == null) {
                throw new UnauthorizedUserException("You're not authorized to make the requested change.");
            }

            if (!updateGameRequest.getPw().equalsIgnoreCase("SuperSecretPW1")) {
                throw new UnauthorizedUserException("You're not authorized to make the requested change.");
            }

            User requestingUser = userDao.getUser(updateGameRequest.getUserId());
            if (requestingUser == null || !requestingUser.getRole().equalsIgnoreCase("admin")) {
                throw new UserNotFoundException("You're not authorized to make the requested change.");
            }
            // Next, if it's a gameTime change request, let's make sure it's not in the past
            if (updateGameRequest.getGameTime() != null) {
                if (System.currentTimeMillis() > Long.parseLong(updateGameRequest.getGameTime())) {
                    throw new InvalidGameAttributeException("You can't update a game time to be in the past.");
                } else {
                    gameToUpdate.setGameTime(updateGameRequest.getGameTime());
                }
            }

            if (updateGameRequest.getLocation() != null) {
                gameToUpdate.setLocation(updateGameRequest.getLocation());
            }

            if (updateGameRequest.getStatus() != null &&
                    !updateGameRequest.getStatus().equalsIgnoreCase(gameToUpdate.getStatus().toString())) {
                gameToUpdate.setStatus(Enum.valueOf(GameStatus.class, updateGameRequest.getStatus()));
            }

            // Player list update
            if (updateGameRequest.getPlayers() != null) {
                List<User> updatedPlayerList = new ArrayList<>();
                for (User player : updateGameRequest.getPlayers()) {
                    User u = userDao.getUser(player.getUserId());
                    updatedPlayerList.add(u);
                }
                gameToUpdate.setPlayers(updatedPlayerList);
            }

            // After the above, we've looked at all the potential update fields, validated any updated, and updated
            //  the game accordingly. Now, let's save it and return the gamemodel as a response.
            System.out.println("Number of players in request: " + updateGameRequest.getPlayers());
            System.out.println("Number of players in updated game: " + gameToUpdate.getPlayers().size());

            Game savedGame = gameDao.saveGame(gameToUpdate);
            return new UpdateGameResult(modelConverter.toGameModel(savedGame));
        }

        return new UpdateGameResult(modelConverter.toGameModel(gameToUpdate));
    }
}
