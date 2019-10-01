package be.afelio.babell.tp_babell.api.jwt.model;


import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private String token;
    public JwtResponse() {}
    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
    	this.token =token;
    }
}
