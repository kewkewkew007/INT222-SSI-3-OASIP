package sit.ssi3.oasip.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import sit.ssi3.oasip.Enum.RoleEnum;
//import sit.ssi3.validations.UniqueEmail;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false,updatable = false)
    private Integer id;


    @NotEmpty(message = "A name must not be empty!")
    @Size(max = 100, message = "A name must be between 1 and 100")
    @Column(name = "name", nullable = false, length = 100 ,unique = true )
    private String name;


    @Email(message = "An email must be well-formed email address", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @NotEmpty(message = "An email address must not be empty!")
    @Size(max = 50, message = "An email address must be between 1 and 50")
    @Column(name = "email", nullable = false, length = 50 )
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEnum role;

    @Column(name = "createdOn" , insertable = false,updatable = false)
    private Date createdOn;

    @Column(name = "updatedOn" , insertable = false,updatable = false)
    private Date updatedOn;

    @Transient
    @AssertFalse(message = "Email must be unique")
    private boolean isEmailUnique;

    @Transient
    @AssertFalse(message = "Name must be unique")
    private boolean isNameUnique;

    @Transient
    @AssertTrue(message = "Role must be specified")
    private boolean isRoleSpecified;

    @OneToMany(mappedBy = "user")
    private Set<Event> events = new LinkedHashSet<>();

}