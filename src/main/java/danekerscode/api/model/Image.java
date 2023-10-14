package danekerscode.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image extends BaseEntity {
    @Column(name = "base_64_data")
    private String base64Data;
    private String name;
}
