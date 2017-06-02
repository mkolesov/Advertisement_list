package Training.Utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Администратор on 02.06.2017.
 */
public class UserInfo {

    public static Map<String, String> getData(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("role", authentication.getAuthorities().iterator().next().getAuthority());

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userData.put("userName", authentication.getName());
        }
        return userData;
    }
}
