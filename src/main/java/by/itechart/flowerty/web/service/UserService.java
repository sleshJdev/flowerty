package by.itechart.flowerty.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.persistence.model.Role;
import by.itechart.flowerty.persistence.model.User;
import by.itechart.flowerty.persistence.repository.ContactRepository;
import by.itechart.flowerty.persistence.repository.RoleRepository;
import by.itechart.flowerty.persistence.repository.UserRepository;
import by.itechart.flowerty.security.service.UserDetailsServiceImpl;
import by.itechart.flowerty.solr.repository.ContactDocumentRepository;
import by.itechart.flowerty.web.model.UserEditBundle;

import java.util.ArrayList;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *         <p/>
 *         service for user repository
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactDocumentRepository contactDocumentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public UserEditBundle getUserEditBundleFor(Long id) {
        UserEditBundle bundle = new UserEditBundle();

        User user = userRepository.findOne(id);
        user.setPassword(null);
        bundle.setUser(user);
        bundle.setContacts((List<Contact>) contactRepository.findAll());
        bundle.setRoles((List<Role>) roleRepository.findAll());

        return bundle;
    }

    public User getUserByLogin(String login) {
        User user = userRepository.findUserByLogin(login);
        user.setPassword(null);

        return user;
    }

    @Transactional
    public User save(User newUser) {
        // remove contact from solr context, because he has user and we can't
        // remove it
        contactDocumentRepository.delete(newUser.getContact().getContactDocument());

        String password = newUser.getPassword();
        newUser.setPassword(passwordEncoder.encode(password));

        return userRepository.save(newUser);
    }

    @Transactional
    public User update(User newUser) {
        User oldUser = userRepository.findOne(newUser.getId());

        newUser.setPassword(oldUser.getPassword());

        return userRepository.save(newUser);
    }

    public Page<User> getPage(int page, int size) {
        return userRepository.findByCompany(userDetailsService.getCurrentContact().getCompany(), new PageRequest(page,
                size));
    }

    public User findUserByLoginAndPassword(String username, String password) {
        return userRepository.findUserByLoginAndPassword(username, password);
    }

    private static List<Long> fetchIdOfContact(List<User> users) {
        List<Long> ids = new ArrayList<>(users.size());
        for (User user : users) {
            ids.add(user.getId());
        }

        return ids;
    }

    public int deleteIdIn(List<User> users) {
        for (User user : users) {
            contactDocumentRepository.save(user.getContact().getContactDocument());
        }

        return userRepository.deleteIdIsIn(fetchIdOfContact(users));
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public List<User> getUsersByRoleName(String roleString) {
        Role.ROLE_TYPE roleType = Role.ROLE_TYPE.valueOf(roleString);
        if (roleType == null) {
            return null;
        }
        Role role = roleRepository.findByName(roleType);
        return userRepository.findByRole(role);
    }

}
