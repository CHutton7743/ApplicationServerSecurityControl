package edu.BellevueCollege.NestedCatjam.ControlCognizant.services;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.UserFilter;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserManagementService {
    private ManagementAPI api;


    @Value("https://${env.AUTH0_DOMAIN}/")
    public String domain;

    @Value("${env.AUTH0_MANAGEMENT_CLIENT_ID}")
    public String clientID;

    @Value("${env.AUTH0_MANAGEMENT_CLIENT_SECRET}")
    public String clientSecret;

    @Value("${env.AUTH0_MANAGEMENT_AUDIENCE}") public String audience;
    private AuthAPI authAPI;

    @Autowired
    public UserManagementService() {

    }
    // TODO: fix this
    private ManagementAPI getApi() throws Auth0Exception{
        if (null == authAPI) {
            authAPI = new AuthAPI(domain, clientID, clientSecret);
        }
        final var request = authAPI.requestToken(audience);
        final var token = request.execute();
        System.out.println(token);

        if (null == this.api) {
            this.api = new ManagementAPI(domain, token.getAccessToken());
        } else {
            this.api.setApiToken(token.getAccessToken());
        }

        return api;
    }
    // TODO: consider returning different type to better encapsulate the use of auth0 and reduce coupling
    public List<User> getUsers() throws Auth0Exception {
        return getApi().users().list(new UserFilter()).execute().getItems();
    }

    public void update(String userId, User user) throws Auth0Exception {
        final var result = getApi().users().update(userId, user).execute();
    }

    public void delete(String userId) throws Auth0Exception {
        getApi().users().delete(userId).execute();
    }

    public User addUser(User user) throws Auth0Exception {
        return getApi().users().create(user).execute();

    }

    public void assignRole(String id, List<String> role) throws Auth0Exception {
        getApi().users().addRoles(id, role).execute();
    }
}
