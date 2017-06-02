package Training.Utils;

import Training.Dao.AdvDAO;
import Training.Entities.Advertisement;
import Training.Entities.Container;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Администратор on 16.05.2017.
 */
public class AdvertisementFileTransformer {
    public static void exportToXml(long [] ids, AdvDAO advDAO, String dest) {
        Container container = new Container();
        for (long id :ids){
            container.add(advDAO.getById(id));
        }
        try {
            File file = new File(dest);
            JAXBContext jaxbContext = JAXBContext.newInstance(Container.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(container, file);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    public static void importFromXml (InputStream file, AdvDAO advDAO){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Container.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Container container = (Container) unmarshaller.unmarshal(file);
            List<Advertisement> list = container.getAdvertisements();
            for (Advertisement adv: list){
                advDAO.add(adv);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
