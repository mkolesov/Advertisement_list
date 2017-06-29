package Training.Spring.security;

import Training.Dao.UserDao;
import Training.Entities.User.SmallUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Администратор on 22.06.2017.
 */


public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        SmallUserEntity user = userDao.findByUserName(userName);
        //ExtendedUserEntity  u = userDao.getFullUserEntity(userName);
        if ( user==null || !user.isEnabled()){
            throw new UsernameNotFoundException("No such user in db");
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        CustomUserDetailsUser customUserDetailsUser = new CustomUserDetailsUser(
                user.getUsername(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAutorities(user.getUsername())
        );

        return customUserDetailsUser;
    }

    public Collection<? extends GrantedAuthority> getAutorities(String name) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(name));
        return authList;
    }

    public List<String> getRoles(String name) {
        List<String> res = new ArrayList<String>();
        userDao.getRolesByName(name).stream().forEach( role -> {
            res.add("ROLE_"+role);
        });
        return res;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List <String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
