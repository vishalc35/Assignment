package supermetrics.client.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Auth {

    @JsonProperty("client_id")
    private String clientId;
    private String email;
    @JsonProperty("sl_token")
    private String token;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "clientId='" + clientId + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
