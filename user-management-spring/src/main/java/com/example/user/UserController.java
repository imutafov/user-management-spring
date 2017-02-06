package com.example.user;

import com.example.exceptions.UserNotFoundException;
import com.example.security.CustomUserDetailsService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private CustomUserDetailsService customService;

    //@PreAuthorize("permitAll")
    //@PostAuthorize("permitAll")
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public User save(@RequestBody User user) {

        Role role = new Role();
        user.setRole(role);

        Privilege privilege = new Privilege();
        privilege.setName("PERM_VIEW_USER");

        Collection<Privilege> privileges = new ArrayList<>();
        privileges.add(privilege);

        role.setPrivileges(privileges);
        user.setRole(role);

        return service.save(user);
    }

    @PreAuthorize("hasAuthority('PERM_VIEW_USER')")
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_USER')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public User remove(@PathVariable Long id) {
        try {
            return service.remove(id);
        } catch (Exception e) {
            throw new UserNotFoundException(id.toString());
        }
    }

    @PreAuthorize("hasAuthority('PERM_EDIT_USER')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public User update(@PathVariable Long id, @RequestBody User user) throws Exception {
        return service.update(id, user);
    }

    @PreAuthorize("hasAuthority('PERM_VIEW_USER')")
    @RequestMapping(value = "/users/search/{firstName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> search(@PathVariable String firstName) throws Exception {
        return service.findByFirstName(firstName);
    }

    @PreAuthorize("hasAuthority('PERM_VIEW_USER')")
    @RequestMapping(value = "/users/sort/lastname/a", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllOrderByLastNameAsc() {
        return service.getAllOrderByLastNameAsc();
    }

    @PreAuthorize("hasAuthority('PERM_VIEW_USER')")
    @RequestMapping(value = "/users/sort/lastname/d", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllOrderByLastNameDesc() {
        return service.getAllOrderByLastNameDesc();
    }

    @PreAuthorize("hasAuthority('PERM_VIEW_USER')")
    @RequestMapping(value = "/users/sort/date/a", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllOrderByDateAsc() {
        return service.getAllOrderByDateAsc();
    }

    @PreAuthorize("hasAuthority('PERM_VIEW_USER')")
    @RequestMapping(value = "/users/sort/date/d", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllOrderByDateDesc() {
        return service.getAllOrderByDateDesc();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/users/flagged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllFlaggedUsers() {
        return customService.getAllFlaggedUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/users/unflagged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllUnflaggedUsers() {
        return customService.getAllUnflaggedUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/change/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public User changeUserFlag(@PathVariable String name) {
        return customService.changeFlag(name);
    }
}
