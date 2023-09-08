package main.java.com.luisreneonate.pittmanparkpickup.dynamodb;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDbClientProvider {
    public static AmazonDynamoDB getDynamoDBClient() {
        return getDynamoDBClient("us-east-1");
    }

    public static AmazonDynamoDB getDynamoDBClient(String region) {
        if (null == region) {
            throw new IllegalArgumentException("Region cannot be null");
        }

        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(region)
                .build();
    }
}
