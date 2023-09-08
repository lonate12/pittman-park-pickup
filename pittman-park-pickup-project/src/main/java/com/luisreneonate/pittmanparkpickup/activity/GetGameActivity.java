package main.java.com.luisreneonate.pittmanparkpickup.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.requests.GetGameRequest;
import main.java.com.luisreneonate.pittmanparkpickup.apimodels.results.GetGameResult;

public class GetGameActivity implements RequestHandler<GetGameRequest, GetGameResult> {
    @Override
    public GetGameResult handleRequest(final GetGameRequest getGameRequest, Context context) {
        return null;
    }
}
