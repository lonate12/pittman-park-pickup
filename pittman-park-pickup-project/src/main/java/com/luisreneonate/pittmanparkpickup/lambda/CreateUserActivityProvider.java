package main.java.com.luisreneonate.pittmanparkpickup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.CreateUserRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.CreateUserResult;
import main.java.com.luisreneonate.pittmanparkpickup.dependency.DaggerServiceComponent;
import main.java.com.luisreneonate.pittmanparkpickup.dependency.ServiceComponent;


public class CreateUserActivityProvider implements RequestHandler<CreateUserRequest, CreateUserResult> {
    private static ServiceComponent daggerServiceComponent;
    public CreateUserActivityProvider() {
    }

    @Override
    public CreateUserResult handleRequest(CreateUserRequest createUserRequest, Context context) {
        return getDaggerServiceComponent().provideCreateUserActivity().handleRequest(createUserRequest, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        if (daggerServiceComponent == null) {
            daggerServiceComponent = DaggerServiceComponent.create();
        }
        return daggerServiceComponent;
    }
}
