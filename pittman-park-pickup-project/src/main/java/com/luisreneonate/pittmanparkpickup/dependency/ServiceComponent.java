package main.java.com.luisreneonate.pittmanparkpickup.dependency;

import main.java.com.luisreneonate.pittmanparkpickup.activity.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component (modules = {DaoModule.class})
public interface ServiceComponent {
    CreateGameActivity provideCreateGameActivity();
    GetGameActivity provideGetGameActivity();
    UpdateGameActivity provideUpdateGameActivity();
    CreateUserActivity provideCreateUserActivity();
}
