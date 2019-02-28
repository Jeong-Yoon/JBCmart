package my.examples.JBCmart.security;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.Role;
import my.examples.JBCmart.domain.User;
import my.examples.JBCmart.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor // final 인 필드만 초기화하는 생성자가 자동으로 만들어진다. 생성자주입시 사용
public class JBCUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = false)
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.getUserById(id);
        if (user == null){
            throw new UsernameNotFoundException(id + "에 해당하는 사용자가 없습니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        //리턴한 값은 세션에 저장된다.
        JBCSecurityUser jbcSecurityUser = new JBCSecurityUser(id, user.getPasswd(), authorities);
        jbcSecurityUser.setEmail(user.getEmail());
        jbcSecurityUser.setName(user.getName());
        jbcSecurityUser.setPhone(user.getPhone());
        return jbcSecurityUser;
    }
}
