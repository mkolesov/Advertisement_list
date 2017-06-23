package Training.Dao.Impl;

import Training.Dao.UserDao;
import Training.Entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Администратор on 22.06.2017.
 */

@Transactional
public class UserDaoJdbcImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity findByUserName(String userName) {
        Query query = entityManager.createQuery("SELECT u FROM UserEntity u where u.username = :user_name", UserEntity.class);
        query.setParameter("user_name", userName);
        return (UserEntity)query.getSingleResult();
    }

    public Set<String> getRolesByName(String userName) {
        Query query = entityManager.createNativeQuery("select group_id from groups_members where user_name = :user_name");
        query.setParameter("user_name", userName);
        List<Integer> user_groups = query.getResultList();
        Set<String> userRoles = new HashSet<String>();
        user_groups.stream().forEach(group -> {
            Query getRolesQuery = entityManager.createNativeQuery("select authority from groups_authorities where group_id = " + Integer.toString(group));
            userRoles.addAll(getRolesQuery.getResultList());
        });
        return userRoles;
    }
}
