import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    @Override
    public void adRole(Role newRole) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(newRole);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public Role getRole(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Role role = entityManager.find(Role.class,id);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return role;
    }

    @Override
    public void changeRole(int id, String roleName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Role role = entityManager.find(Role.class,id);
        role.setRoleName(roleName);
        entityManager.merge(role);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void deleteRole(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Role role = entityManager.find(Role.class,id);
        entityManager.remove(role);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void getAllRoles() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        String jpqlQuery = "from Role";
        TypedQuery<Role> query = entityManager.createQuery(jpqlQuery, Role.class);
        List<Role> roles = query.getResultList();
        entityManager.getTransaction().commit();
        for (Role role : roles) {
            System.out.println("Идентификатор: " + role.getId());
            System.out.println("Роль: " + role.getRoleName());
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}
