package safe;

import safe.domain.User;
import safe.repository.UserRepository;
import safe.repository.UserRepositoryImpl;
import safe.service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user = new User(1, "test@gmail.com", "123");
        User user2 = new User(2, "zzz@gmail.com", "123");

        UserRepository repository = new UserRepositoryImpl();
        UserService service = new UserService(repository);

        service.createTableUsers();
//        service.createUser(user2);

        System.out.println(service.findAll());

    }
}