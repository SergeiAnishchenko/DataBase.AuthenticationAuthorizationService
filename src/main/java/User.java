import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "userProfileCreationDateAndTime")
    private LocalDateTime userProfileCreationDateAndTime;

    @ManyToMany
    @JoinTable(
            name = "tableUsersAndRoles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    public User() {
    }

    public User(String userName, String login, String password, LocalDateTime userProfileCreationDateAndTime, List<Role> roles) {
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.userProfileCreationDateAndTime = userProfileCreationDateAndTime;
        this.roles = roles;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName != null && !userName.isEmpty() && !userName.isBlank()) {
            this.userName = userName;

        } else {
            try {
                throw new IncorrectArgumentException("Имя пользователя");
            } catch (IncorrectArgumentException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login != null && !login.isEmpty() && !login.isBlank()) {
            this.login = login;

        } else {
            try {
                throw new IncorrectArgumentException("Логин");
            } catch (IncorrectArgumentException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && !password.isEmpty() && !password.isBlank()) {
            this.password = password;

        } else {
            try {
                throw new IncorrectArgumentException("Пароль");
            } catch (IncorrectArgumentException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public LocalDateTime getUserProfileCreationDateAndTime() {
        return userProfileCreationDateAndTime;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id= " + id +
                ", userName='" + userName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userProfileCreationDateAndTime=" + userProfileCreationDateAndTime +
                ", roles=" + roles +
                '}';
    }
}
