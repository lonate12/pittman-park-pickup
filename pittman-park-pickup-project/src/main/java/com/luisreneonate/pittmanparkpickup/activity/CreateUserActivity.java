package main.java.com.luisreneonate.pittmanparkpickup.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.UserModel;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.CreateUserRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.CreateUserResult;
import main.java.com.luisreneonate.pittmanparkpickup.converters.ModelConverter;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.UserDao;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.User;
import main.java.com.luisreneonate.pittmanparkpickup.exceptions.UserAlreadyExistsException;

import javax.inject.Inject;

public class CreateUserActivity implements RequestHandler<CreateUserRequest, CreateUserResult> {
    private final UserDao userDao;
    private final ModelConverter modelConverter;

    @Inject
    public CreateUserActivity(UserDao userDao, ModelConverter modelConverter) {
        this.userDao = userDao;
        this.modelConverter = modelConverter;
    }

    @Override
    public CreateUserResult handleRequest(CreateUserRequest createUserRequest, Context context) {
        System.out.println(createUserRequest);

        // User that's in the DB
        boolean doesUserAlreadyExist = userDao.doesUserAlreadyExist(createUserRequest.getEmail());

        if (doesUserAlreadyExist) {
            throw new UserAlreadyExistsException("This email is already in use. Cannot create a user with this email.");
        }

        // If not already in the DB, create a new user and save to DB
        User user = new User(createUserRequest.getFirstName(), createUserRequest.getLastName(), createUserRequest.getEmail());
        User savedUser = userDao.saveUser(user);

        UserModel userModel = modelConverter.toUserModel(savedUser);

        return CreateUserResult.builder().withUser(userModel).build();
    }
}
