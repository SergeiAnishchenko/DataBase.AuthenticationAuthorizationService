import java.time.LocalDateTime;
import java.util.List;

public interface UserDAO {
    void getAllUsers();

    User getUser(int userID);

    void getUsersListByRole(String roleName);

    void deleteUser(int userID);

    void adUser(User newUser);

    void changeUser(int userID, String userName, String login, String password,
                    LocalDateTime userProfileCreationDateAndTime,List<Role> roles);


}
