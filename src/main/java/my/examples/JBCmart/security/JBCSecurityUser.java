package my.examples.JBCmart.security;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class JBCSecurityUser extends User {
    private String name;
    private String phone;
    private String email;

    public JBCSecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getUserId(){
        return super.getUsername();
    }
}
