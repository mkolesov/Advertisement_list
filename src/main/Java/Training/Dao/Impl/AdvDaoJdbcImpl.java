package Training.Dao.Impl;

import Training.Dao.AdvDAO;
import Training.Entities.Advertisement.Advertisement;
import Training.Entities.Advertisement.Photo;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Администратор on 25.04.2017.
 */
@Transactional
public class AdvDaoJdbcImpl implements AdvDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public List<Advertisement> list(boolean isInBasket) {
        Query query = entityManager.createQuery("SELECT a FROM Advertisement a where a.inBasket = :in_basket", Advertisement.class);
        query.setParameter("in_basket", isInBasket);
        return (List<Advertisement>) query.getResultList();
    }

    public List<Advertisement> list(boolean isInBasket, String pattern) {
        Query query = entityManager.createQuery("SELECT a FROM Advertisement a where a.shortDesc like :pattern and a.inBasket = :in_basket", Advertisement.class);
        query.setParameter("pattern", "%"+pattern+"%");
        query.setParameter("in_basket", isInBasket);
        return (List<Advertisement>) query.getResultList();
    }

    public void add(Advertisement advertisement) {
        entityManager.persist(advertisement);
        Query query = entityManager.createNativeQuery("UPDATE users SET adv_count = adv_count+1 where user_name = :name");
        query.setParameter("name", advertisement.getCreaterUserName());
        query.executeUpdate();
    }

    public void delete(long id) {
        Advertisement advertisement = entityManager.find(Advertisement.class, id);
        Query query = entityManager.createNativeQuery("UPDATE users SET adv_count = adv_count-1 where user_name = :name");
        query.setParameter("name", advertisement.getCreaterUserName());
        query.executeUpdate();
        entityManager.remove(advertisement);
    }

    public void changeBasketStatus(boolean status, long id) {
            Advertisement advertisement = entityManager.find(Advertisement.class, id);
            advertisement.setInBasket(status);
            entityManager.merge(advertisement);
    }

    public byte[] getPhoto(long id) {
        try {
            Photo photo = entityManager.find(Photo.class, id);
            return photo.getBody();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public Advertisement getById(long id) {
        Query query = entityManager.createQuery("SELECT a FROM Advertisement a where a.id = :adv_id", Advertisement.class);
        query.setParameter("adv_id", id);
        return (Advertisement) query.getSingleResult();
    }

    @Override
    public boolean inBasket(long id) {
        Query query = entityManager.createNativeQuery("select in_basket from advs where id = :id");
        query.setParameter("id", id);
        return (boolean)query.getSingleResult();
    }
}
