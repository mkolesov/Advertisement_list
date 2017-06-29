package Training.Dao.Impl;

import Training.Dao.UserDao;
import Training.Entities.User.ExtendedUserEntity;
import Training.Entities.User.SmallUserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collections;
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

    public SmallUserEntity findByUserName(String userName) {
        Query query = entityManager.createQuery("SELECT u FROM SmallUserEntity u where u.username = :user_name", SmallUserEntity.class);
        query.setParameter("user_name", userName);
        try {
            return (SmallUserEntity)query.getSingleResult();
        } catch (NoResultException ex){
            return null;
        }
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

    @Override
    public boolean isAdvAdditionAllowed() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Query query = entityManager.createNativeQuery("select group_id from groups_members where user_name = :user_name");
//        query.setParameter("user_name", authentication.getName());
//        List<Integer> user_groups = query.getResultList();
//        Set<Integer> groupsPremissions = new HashSet<>();
//        user_groups.stream().forEach(group -> {
//            Query getMaxAdvCount = entityManager.createNativeQuery("select adv_maximum from user_groups where id = " + Integer.toString(group));
//            groupsPremissions.add((Integer)getMaxAdvCount.getSingleResult());
//        });
//        int maxAllowedAdvCount = Collections.max(groupsPremissions);
//        Query countQuery = entityManager.createNativeQuery("select adv_count from users where user_name = :user_name");
//        countQuery.setParameter("user_name", authentication.getName());
//        int currentUserAdvCount = (int)countQuery.getSingleResult();
//        if (currentUserAdvCount<maxAllowedAdvCount){
//            return true;
//        }
        return false;
    }

    @Override
    public ExtendedUserEntity getFullUserEntity(String userName) {
        Query query = entityManager.createQuery("SELECT u FROM ExtendedUserEntity u where u.username = :user_name", ExtendedUserEntity.class);
        query.setParameter("user_name", userName);
        try {
            return (ExtendedUserEntity) query.getSingleResult();
        } catch (NoResultException ex){
            return null;
        }
    }
}
