package danekerscode.api.domain.model;

import danekerscode.api.common.constants.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Customer extends BaseEntity {
    private String name;
    private String surname;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String imageUrl;
}
