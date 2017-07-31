package Training.Dao.Impl;

import Training.Dao.UserDao;
import Training.Entities.SecurityGroups.GroupEntity;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Query getExtendedUserQuery = entityManager.createQuery("select u from ExtendedUserEntity u where u.username = :user_name", ExtendedUserEntity.class);
        getExtendedUserQuery.setParameter("user_name", authentication.getName());
        ExtendedUserEntity extendedUserEntity = (ExtendedUserEntity)getExtendedUserQuery.getSingleResult();
        int maxAllowedAdvCount = 0;
        for (GroupEntity group : extendedUserEntity.getGroups()) {
            if (group.getAdvMaximumCount() > maxAllowedAdvCount) {
                maxAllowedAdvCount = group.getAdvMaximumCount();
            }
        }
        int currentUserAdvCount = extendedUserEntity.getAdvCount();
        if (currentUserAdvCount<maxAllowedAdvCount){
            return true;
        }
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
