package Training.Dao;

import Training.Entities.Advertisement.Advertisement;

import java.util.List;

/**
 * Created by Администратор on 25.04.2017.
 */
public interface AdvDAO {
    List<Advertisement> list(boolean isInBasket);
    List<Advertisement> list(boolean isInBasket, String pattern);
    void add(Advertisement advertisement);
    void delete (long id);
    byte[] getPhoto(long id);
    void changeBasketStatus(boolean status, long id);
    Advertisement getById (long id);
    boolean inBasket (long id);
}
