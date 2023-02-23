package demo.ecommerce.service;


import demo.ecommerce.keycloak.Credentials;
import demo.ecommerce.dto.UserDTO;
import demo.ecommerce.keycloak.KeycloakConfig;
import demo.ecommerce.model.Customer;
import demo.ecommerce.model.Merchant;
import demo.ecommerce.repository.CustomerRepo;
import demo.ecommerce.repository.MerchantRepo;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class KeycloakService {
    @PersistenceContext
    EntityManager em;

    private final static String MERCHANTS_GROUP_NAME = "merchants";
    private final static String CUSTOMERS_GROUP_NAME = "customers";

    public void addCustomer(UserDTO userDTO) {
        CredentialRepresentation credential = Credentials.createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        user.setEmailVerified(false);
        user.setGroups(Collections.singletonList(CUSTOMERS_GROUP_NAME));
        UsersResource instance = getInstance();
        Response response = instance.create(user);
        user = getUser(userDTO.getUserName());
        Customer customer = userDTO.toCustomerEntity();
        customer.setKeycloakId(user.getId());
        em.persist(customer);
    }

    public void addMerchant(UserDTO userDTO) {
        CredentialRepresentation credential = Credentials.createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        user.setEmailVerified(false);
        user.setGroups(Collections.singletonList(MERCHANTS_GROUP_NAME));
        UsersResource instance = getInstance();
        Response response = instance.create(user);
        user = getUser(userDTO.getUserName());
        Merchant merchant = userDTO.toMerchantEntity();
        merchant.setKeycloakId(user.getId());
        em.persist(merchant);
    }


    public UserRepresentation getUser(String userName) {
        UsersResource usersResource = getInstance();
        List<UserRepresentation> user = usersResource.search(userName, true);
        return user.size() != 0 ? user.get(0) : null;

    }

    public void updateUser(String userId, UserDTO userDTO) {
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }

    public void deleteUser(String userId) {
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }


    public void sendVerificationLink(String userId) {
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .sendVerifyEmail();
    }

    public void sendResetPassword(String userId) {
        UsersResource usersResource = getInstance();

        usersResource.get(userId)
                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }

    public UsersResource getInstance() {
        return KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
    }


}
