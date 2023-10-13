package danekerscode.api.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image extends BaseEntity {
    private String base64Data;
    private String name;
}
