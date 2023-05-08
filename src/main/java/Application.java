import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        RoleDAO roleDAO = new RoleDAOImpl();
        UserDAO userDAO = new UserDAOImpl();

        Role role1 = new Role("Разработчик");
        Role role2 = new Role("Аналитик");
        Role role3 = new Role("Тестировщик");
        Role role4 = new Role("Менеджер");
        Role role5 = new Role("Дизайнер");
        Role role6 = new Role("По умолчанию");

        List<Role> rolesForUser1 = new ArrayList<>();
        rolesForUser1.add(role1);
        rolesForUser1.add(role3);

        List<Role> rolesForUser2 = new ArrayList<>();
        rolesForUser2.add(role1);
        rolesForUser2.add(role2);
        rolesForUser2.add(role3);

        List<Role> rolesForUser3 = new ArrayList<>();
        rolesForUser3.add(role4);
        rolesForUser3.add(role6);

        List<Role> rolesForUser4 = new ArrayList<>();
        rolesForUser4.add(role5);


        User user1 = new User(
                "Василий Иванов",
                "Вася",
                "Пароль Василия",
                LocalDateTime.of(2023, 3, 20, 15, 0),
                rolesForUser1
        );

        User user2 = new User(
                "Елена Федорова",
                "Лена",
                "Пароль Елены",
                LocalDateTime.of(2023, 2, 11, 11, 0),
                rolesForUser2
        );

        User user3 = new User(
                "Ольга Соколова",
                "Оля",
                "Пароль Ольги",
                LocalDateTime.of(2023, 3, 21, 16, 0),
                rolesForUser3
        );

        User user4 = new User(
                "Иван Орехов",
                "Ваня",
                "Пароль Ивана",
                LocalDateTime.of(2023, 1, 1, 17, 0),
                rolesForUser4
        );


        roleDAO.adRole(role1);
        roleDAO.adRole(role2);
        roleDAO.adRole(role3);
        roleDAO.adRole(role4);
        roleDAO.adRole(role5);
        roleDAO.adRole(role6);

        userDAO.adUser(user1);
        userDAO.adUser(user2);
        userDAO.adUser(user3);
        userDAO.adUser(user4);


        //получение общего списка пользователей из базы данных (без ролей)
        userDAO.getAllUsers();


        //получение конкретного пользователя (с его ролями) из базы данных
        System.out.println(userDAO.getUser(1));


        //получение списка пользователей по конкретной роли
        userDAO.getUsersListByRole("Разработчик");


        //удаление пользователя в базе данных
        userDAO.deleteUser(4);


        //добавление нового пользователя с ролями в базу данных
        User user5 = new User(
                "Олег Антонов",
                "Олег",
                "Пароль Олега",
                LocalDateTime.of(2023, 1, 1, 17, 0),
                rolesForUser4
        );
        userDAO.adUser(user5);


        //редактирование существующего пользователя в базе данных
        userDAO.changeUser(6,
                "Алексей Изотов",
                "Леша",
                "Пароль Алексея",
                LocalDateTime.of(2023, 1, 1, 17, 0),
                rolesForUser4
        );
    }
}
