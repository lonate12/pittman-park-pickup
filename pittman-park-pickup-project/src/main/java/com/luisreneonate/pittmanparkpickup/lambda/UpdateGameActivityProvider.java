package main.java.com.luisreneonate.pittmanparkpickup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.UpdateGameRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.UpdateGameResult;
import main.java.com.luisreneonate.pittmanparkpickup.dependency.DaggerServiceComponent;
import main.java.com.luisreneonate.pittmanparkpickup.dependency.ServiceComponent;

public class UpdateGameActivityProvider implements RequestHandler<UpdateGameRequest, UpdateGameResult> {
    private static ServiceComponent daggerServiceComponent;

    public UpdateGameActivityProvider() {
    }


    @Override
    public UpdateGameResult handleRequest(UpdateGameRequest updateGameRequest, Context context) {
        return getDaggerServiceComponent().provideUpdateGameActivity().handleRequest(updateGameRequest, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        if (daggerServiceComponent == null) {
            daggerServiceComponent = DaggerServiceComponent.create();
        }
        return daggerServiceComponent;
    }
}
