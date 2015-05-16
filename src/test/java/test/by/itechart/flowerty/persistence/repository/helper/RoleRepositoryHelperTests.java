package test.by.itechart.flowerty.persistence.repository.helper;

import by.itechart.flowerty.persistence.model.Role;

/**
 * Created by Rostislav on 16-May-15
 */

public abstract class RoleRepositoryHelperTests {

    public static Role getRoleWithIdOne() {
        Role role = new Role();
        role.setId(1L);
        role.setName(Role.ROLE_TYPE.ADMIN);

        return role;
    }
}
