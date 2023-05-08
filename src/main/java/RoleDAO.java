public interface RoleDAO {
    void adRole(Role newRole);

    Role getRole(int id);

    void changeRole(int id, String roleName);

    void deleteRole(int id);

    void getAllRoles();
}
