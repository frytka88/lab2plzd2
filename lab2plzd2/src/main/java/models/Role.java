package models;

import javax.persistence.*;
import java.sql.Types;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)//przechowywane w postaci string
    @Column(name = "type", nullable = false)
    private Types type;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(){};

    public Role(Types type){
        this.type = type;
    }

    public enum Types{
        ROLE_ADMIN,
        ROLE_USER
    }
}
