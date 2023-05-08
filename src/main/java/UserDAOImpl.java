import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class UserDAOImpl implements UserDAO {
    @Override
    public void getAllUsers() {
        System.out.println("Общий список пользователей без указания ролей:");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        String jpqlQuery = "from User";
        TypedQuery<User> query = entityManager.createQuery(jpqlQuery, User.class);
        List<User> users = query.getResultList();
        entityManager.getTransaction().commit();
        for (User user : users) {
            System.out.println("Данные пользователя " + user.getUserName() + " :");
            System.out.println("Идентификатор: " + user.getID());
            System.out.println("Логин: " + user.getLogin());
            System.out.println("Пароль: " + user.getPassword());
            System.out.println("Дата и время создания профиля: " + user.getUserProfileCreationDateAndTime());
            System.out.println();
        }
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public User getUser(int userID) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class,userID);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return user;
    }

    @Override
    public void getUsersListByRole(String roleName) {
        System.out.println("Пользователи с ролью " + roleName + ": ");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        String jpqlQuery = "select user from User user join fetch user.roles role where role.roleName = :roleName";
        TypedQuery<User> query = entityManager.createQuery(jpqlQuery, User.class);
        query.setParameter("roleName", roleName);
        List<User> users = query.getResultList();
        entityManager.getTransaction().commit();
        for (User user : users) {
            System.out.println(user.getUserName());
            System.out.println();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    public void deleteUser(int userID) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class,userID);
        entityManager.remove(user);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void adUser(User newUser) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(newUser);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void changeUser(int userID,
                           String userName,
                           String login,
                           String password,
                           LocalDateTime userProfileCreationDateAndTime,
                           List<Role> roles) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userID);
        user.setUserName(userName);
        user.setLogin(login);
        user.setPassword(password);
        user.setRoles(roles);
        entityManager.merge(user);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
