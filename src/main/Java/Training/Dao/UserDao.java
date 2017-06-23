package Training.Dao;

import Training.Entities.UserEntity;

import java.util.Set;

/**
 * Created by Администратор on 22.06.2017.
 */
public interface UserDao {
    public UserEntity findByUserName (String userName);
    public Set<String> getRolesByName (String userName);
}
