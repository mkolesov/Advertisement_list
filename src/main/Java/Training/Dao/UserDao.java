package Training.Dao;

import Training.Entities.User.ExtendedUserEntity;
import Training.Entities.User.SmallUserEntity;

import java.util.Set;

/**
 * Created by Администратор on 22.06.2017.
 */
public interface UserDao {
    public SmallUserEntity findByUserName (String userName);
    public ExtendedUserEntity getFullUserEntity (String userName);
    public Set<String> getRolesByName (String userName);
    public boolean isAdvAdditionAllowed();
}
