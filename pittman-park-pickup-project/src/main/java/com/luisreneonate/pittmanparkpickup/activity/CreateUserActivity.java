package main.java.com.luisreneonate.pittmanparkpickup.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.CreateUserRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.CreateUserResult;

public class CreateUserActivity implements RequestHandler<CreateUserRequest, CreateUserResult> {

    @Override
    public CreateUserResult handleRequest(CreateUserRequest input, Context context) {
        return null;
    }
}
