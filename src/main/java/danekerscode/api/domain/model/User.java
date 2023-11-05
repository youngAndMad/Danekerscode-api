package danekerscode.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @JsonIgnore
    private String apiKey;

    private Boolean emailVerified;

    @JsonIgnore
    private Integer otp;

    public void verifyEmail(String apiKey){
        this.emailVerified = true;
        this.otp = null;
        this.apiKey = apiKey;
    }
}
