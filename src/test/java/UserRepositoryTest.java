import com.example.backend.models.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.repositories.UserRepositoryImpl;
import org.sqlite.SQLiteDataSource;

import java.util.Optional;

public class UserRepositoryTest {
    public static void main(String[] args) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:survey.db");

        UserRepository userRepository = new UserRepositoryImpl(dataSource);

        // 测试保存用户
        userRepository.registerUser("testUser", "testPassword");

        // 测试查找用户
        Optional<User> user = userRepository.findByUsername("testUser");
        if (user.isPresent()) {
            System.out.println("User found: " + user.get().getUsername());
        } else {
            System.out.println("User not found");
        }
    }
}