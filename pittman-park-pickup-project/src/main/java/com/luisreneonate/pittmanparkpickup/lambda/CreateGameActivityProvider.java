package main.java.com.luisreneonate.pittmanparkpickup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.CreateGameRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.CreateGameResult;
import main.java.com.luisreneonate.pittmanparkpickup.dependency.DaggerServiceComponent;
import main.java.com.luisreneonate.pittmanparkpickup.dependency.ServiceComponent;

public class CreateGameActivityProvider implements RequestHandler<CreateGameRequest, CreateGameResult> {
    private static ServiceComponent daggerServiceComponent;

    public CreateGameActivityProvider() {}

    @Override
    public CreateGameResult handleRequest(CreateGameRequest createGameRequest, Context context) {
        return getDaggerServiceComponent().provideCreateGameActivity().handleRequest(createGameRequest, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        if (daggerServiceComponent == null) {
            daggerServiceComponent = DaggerServiceComponent.create();
        }
        return daggerServiceComponent;
    }
}
