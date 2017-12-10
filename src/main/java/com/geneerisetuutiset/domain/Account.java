package com.geneerisetuutiset.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Account extends AbstractPersistable<Long> {

    @Id
    private Long id;
    @Size(min = 3, max = 12)
    private String username;
    @Size(min = 8)
    private String password;
    @OneToMany
    private List<Role> roles;
}
