package demo.ecommerce.keycloak;

import lombok.NoArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class KeycloakConfig {

    static Keycloak keycloak = null;
    final static String serverUrl = "http://localhost:8080/";
    public final static String realm = "ecommerce";
    final static String clientId = "backend";
    final static String clientSecret = "";
    final static String userName = "ahmedasal";
    final static String password = "root";

    public static Keycloak getInstance() {
        if (keycloak == null) {

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .build();

        }
        return keycloak;
    }
  public static Keycloak newKeycloakBuilderWithPasswordCredentials(String username, String password){
        if (keycloak == null) {

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(username)
                    .password(password)
                    .clientId(clientId)
                    .build();

        }
        return keycloak;
    }


}