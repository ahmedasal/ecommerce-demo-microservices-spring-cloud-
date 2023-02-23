package demo.ecommerce.controller;



import com.sun.istack.NotNull;
import demo.ecommerce.dto.LoginRequest;
import demo.ecommerce.dto.UserDTO;
import demo.ecommerce.keycloak.KeycloakConfig;
import demo.ecommerce.service.KeycloakService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import java.util.List;

@RestController

@RequestMapping(path = "api/user")
public class KeyCloakController {

    @Autowired
    KeycloakService service;
    @Autowired
    KeycloakConfig keycloakConfig;


    @PostMapping("/addcustomer")
    public String addCustomer(@RequestBody UserDTO userDTO){
        service.addCustomer(userDTO);
        return "User Added Successfully.";
    }
    @PostMapping("/addmerchant")
    public String addMerchant(@RequestBody UserDTO userDTO){
        service.addMerchant(userDTO);
        return "User Added Successfully.";
    }
    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LoginRequest loginRequest) {
        Keycloak keycloak = KeycloakConfig.newKeycloakBuilderWithPasswordCredentials(loginRequest.getUsername(), loginRequest.getPassword());

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }
    }
    @GetMapping(path = "/{userName}")
    public UserRepresentation getUser(@PathVariable("userName") String userName){
        UserRepresentation user = service.getUser(userName);
        return user;
    }

    @PutMapping(path = "/update/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @RequestBody UserDTO userDTO){
        service.updateUser(userId, userDTO);
        return "User Details Updated Successfully.";
    }

    @DeleteMapping(path = "/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        service.deleteUser(userId);
        return "User Deleted Successfully.";
    }

    @GetMapping(path = "/verification-link/{userId}")
    public String sendVerificationLink(@PathVariable("userId") String userId){
        service.sendVerificationLink(userId);
        return "Verification Link Send to Registered E-mail Id.";
    }

    @GetMapping(path = "/reset-password/{userId}")
    public String sendResetPassword(@PathVariable("userId") String userId){
        service.sendResetPassword(userId);
        return "Reset Password Link Send Successfully to Registered E-mail Id.";
    }
}