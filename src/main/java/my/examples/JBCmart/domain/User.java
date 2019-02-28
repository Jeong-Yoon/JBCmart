package my.examples.JBCmart.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @Column(length = 255,name = "user_id")
    private String userId;
    @Column(length = 255)
    private String passwd;
    @Column(length = 50)
    private String name;
    @Column(length = 11)
    private String phone;
    @Column(length = 50)
    private String email;
    private Date regdate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<Role> roles;

    @OneToMany(mappedBy = "cartId.user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Cart> carts;

    public User(){
        regdate = new Date();
        roles = new HashSet<>();
        carts = new ArrayList<>();
    }

    public void addRole(Role role) {
        if (roles == null){
            roles = new HashSet<>();
        }
        roles.add(role);
    }
}

/*
| user_id | varchar(255) | NO   | PRI | NULL              |                   |
| passwd  | varchar(255) | NO   |     | NULL              |                   |
| name    | varchar(50)  | NO   |     | NULL              |                   |
| phone   | varchar(11)  | NO   |     | NULL              |                   |
| email   | varchar(50)  | NO   |     | NULL              |                   |
| regdate | datetime     | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
 */