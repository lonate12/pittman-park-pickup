package main.java.com.luisreneonate.pittmanparkpickup.dependency;

import dagger.Component;
import main.java.com.luisreneonate.pittmanparkpickup.activity.CreateGameActivity;
import main.java.com.luisreneonate.pittmanparkpickup.activity.CreateUserActivity;
import main.java.com.luisreneonate.pittmanparkpickup.activity.GetGameActivity;
import main.java.com.luisreneonate.pittmanparkpickup.activity.UpdateGameActivity;

import javax.inject.Singleton;

@Singleton
@Component (modules = {DaoModule.class})
public interface ServiceComponent {
    CreateGameActivity provideCreateGameActivity();
    GetGameActivity provideGetGameActivity();
    UpdateGameActivity provideUpdateGameActivity();
    CreateUserActivity provideCreateUserActivity();
}
