package main.java.com.luisreneonate.pittmanparkpickup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.GetGameRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.GetGameResult;
import main.java.com.luisreneonate.pittmanparkpickup.dependency.DaggerServiceComponent;
import main.java.com.luisreneonate.pittmanparkpickup.dependency.ServiceComponent;

public class GetGameActivityProvider implements RequestHandler<GetGameRequest, GetGameResult> {

    private static ServiceComponent daggerServiceComponent;

    public GetGameActivityProvider() {}

    @Override
    public GetGameResult handleRequest(GetGameRequest getGameRequest, Context context) {

        return getDaggerServiceComponent().provideGetGameActivity().handleRequest(getGameRequest, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        if (daggerServiceComponent == null) {
            daggerServiceComponent = DaggerServiceComponent.create();
        }

        return daggerServiceComponent;
    }
}
