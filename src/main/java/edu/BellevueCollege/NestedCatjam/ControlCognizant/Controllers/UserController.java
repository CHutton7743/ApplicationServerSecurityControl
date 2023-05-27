package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.services.UserManagementService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired private UserManagementService userManagementService;

    @GetMapping
    @ResponseBody
    public List<User> retrieveAllUsers() throws Auth0Exception {
        try {
            return userManagementService.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
    }

    @PostMapping
    @Transactional
    public User postUser(@Valid @RequestBody com.auth0.json.mgmt.users.User user) throws Auth0Exception {
       return userManagementService.addUser(user);


    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getUserById(@PathVariable long id) {
        throw new NotImplementedException();
    }

    @PostMapping(value = "/{id}/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void assignRole(@PathVariable String id, @RequestBody List<String> role) throws Auth0Exception {
        userManagementService.assignRole(id, role);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@PathVariable String id) throws Auth0Exception {
        try {
            userManagementService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public void updateUser(@PathVariable String id, @RequestBody User user) throws Auth0Exception {
        userManagementService.update(id, user);
    }
}
