package com.example.user;

import com.example.exceptions.UserNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

//    @Autowired
//    private MyUserDetailsService customService;
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public User save(@RequestBody User user) {
        return service.save(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public User remove(@PathVariable Long id) {
        try {
            return service.remove(id);
        } catch (Exception e) {
            throw new UserNotFoundException(id.toString());
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public User update(@PathVariable Long id, @RequestBody User user) throws Exception {
        return service.update(id, user);
    }

    @RequestMapping(value = "/users/search/{firstName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> search(@PathVariable String firstName) throws Exception {
        return service.findByFirstName(firstName);
    }

    @RequestMapping(value = "/users/sort/lastname/a", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllOrderByLastNameAsc() {
        return service.getAllOrderByLastNameAsc();
    }

    @RequestMapping(value = "/users/sort/lastname/d", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllOrderByLastNameDesc() {
        return service.getAllOrderByLastNameDesc();
    }

    @RequestMapping(value = "/users/sort/date/a", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllOrderByDateAsc() {
        return service.getAllOrderByDateAsc();
    }

    @RequestMapping(value = "/users/sort/date/d", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllOrderByDateDesc() {
        return service.getAllOrderByDateDesc();
    }
//
//    @RequestMapping(value = "/users/flagged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<User> getAllFlaggedUsers() {
//        return customService.getAllFlaggedUsers();
//    }
//
//    @RequestMapping(value = "/users/unflagged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<User> getAllUnflaggedUsers() {
//        return customService.getAllUnflaggedUsers();
//    }
//
//    @RequestMapping(value = "/user/change/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public User changeUserFlag(@PathVariable Long id, @RequestBody User user) {
//        return customService.changeFlag(id, user);
//}
}
