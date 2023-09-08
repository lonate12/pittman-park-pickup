package main.java.com.luisreneonate.pittmanparkpickup.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.DynamoDbClientProvider;

import javax.inject.Singleton;

@Module
public class DaoModule {
    @Provides
    @Singleton
    public DynamoDBMapper provideDynamoDBMapper() {
        return new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
    }
}
