package Training.Utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 02.06.2017.
 */
public class UserInfo {

    public static List<String> getCurrentUserRoles(){
        List<String> res = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach( authority->{
            res.add(authority.getAuthority());
        });
        return res;
    }

    public static String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  authentication.getName();
    }
}
