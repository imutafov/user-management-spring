package com.example.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    public User save(User user) {
        user.setEnabled(false);
        user.setRole(roleRepo.findByName("USER"));
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User remove(Long id) throws Exception {
        User user = repo.findOne(id);
        if (user == null) {
            throw new Exception("User not found");
        }
        repo.delete(user);
        return user;
    }

    public User update(Long id, User user) throws Exception {
        User dbUser = repo.findOne(id);
        if (user == null) {
            throw new Exception("User not found");
        }
        dbUser.setUserName(user.getUsername());
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setBirthDate(user.getBirthDate());
        dbUser.setPhoneNumber(user.getPhoneNumber());
        dbUser.setEmail(user.getEmail());
        return repo.save(dbUser);
    }

    public List<User> findByFirstName(String firstName) {
        return repo.findByFirstNameLike(firstName);
    }

    public List<User> getAllOrderByLastNameAsc() {
        return repo.findAllByOrderByLastName();
    }

    public List<User> getAllOrderByLastNameDesc() {
        return repo.findAllByOrderByLastNameDesc();
    }

    public List<User> getAllOrderByDateAsc() {
        return repo.findAllByOrderByLastName();
    }

    public List<User> getAllOrderByDateDesc() {
        return repo.findAllByOrderByLastNameDesc();
    }

    public List<User> getAllFlaggedUsers() {
        return repo.findByEnabledTrue();
    }

    public List<User> getAllUnflaggedUsers() {
        return repo.findByEnabledFalse();
    }

    public User changeFlag(String name) {
        User user = repo.findByUserName(name);
        user.setEnabled(!user.isEnabled());
        repo.save(user);
        return user;
    }
}
