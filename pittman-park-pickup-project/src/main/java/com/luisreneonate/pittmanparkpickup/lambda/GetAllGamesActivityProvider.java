package main.java.com.luisreneonate.pittmanparkpickup.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.GetAllGamesRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.GetAllGamesResult;
import main.java.com.luisreneonate.pittmanparkpickup.dependency.DaggerServiceComponent;
import main.java.com.luisreneonate.pittmanparkpickup.dependency.ServiceComponent;

public class GetAllGamesActivityProvider implements RequestHandler<GetAllGamesRequest, GetAllGamesResult> {
    private static ServiceComponent daggerServiceComponent;

    public GetAllGamesActivityProvider() {
    }

    @Override
    public GetAllGamesResult handleRequest(GetAllGamesRequest getAllGamesRequest, Context context) {
        return getDaggerServiceComponent().provideGetAllGamesActivity().handleRequest(getAllGamesRequest, context);
    }

    private ServiceComponent getDaggerServiceComponent() {
        if (daggerServiceComponent == null) {
            daggerServiceComponent = DaggerServiceComponent.create();
        }
        return daggerServiceComponent;
    }
}
