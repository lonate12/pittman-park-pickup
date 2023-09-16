package main.java.com.luisreneonate.pittmanparkpickup.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import main.java.com.luisreneonate.pittmanparkpickup.dynamodb.models.User;
import main.java.com.luisreneonate.pittmanparkpickup.exceptions.UserNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public UserDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public User getUser(String userId) {
        User user = this.dynamoDBMapper.load(User.class, userId);

        if (user == null) {
            throw new UserNotFoundException("Could not find player with id " + userId);
        }

        return user;
    }

    public User saveUser(User user) {
        this.dynamoDBMapper.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        // TODO: Get all users
        return new ArrayList<>();
    }

    public boolean doesUserAlreadyExist(String emailToCheck) {
        User user = new User();
        user.setEmail(emailToCheck);
        // Creating the query
        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withHashKeyValues(user)
                .withIndexName("email-index")
                .withConsistentRead(false);

        return this.dynamoDBMapper.query(User.class, queryExpression).size() > 0;
    }
}
