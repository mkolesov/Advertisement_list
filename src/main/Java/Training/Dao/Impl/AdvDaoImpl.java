package Training.Dao.Impl;

import Training.Dao.AdvDAO;
import Training.Entities.Advertisement;
import Training.Entities.Photo;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Администратор on 25.04.2017.
 */
@Transactional
public class AdvDaoImpl implements AdvDAO{

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
    }

    public void delete(long id) {
            Advertisement advertisement = entityManager.find(Advertisement.class, id);
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
}
