package Training.Utils.XML;

import Training.Dao.AdvDAO;
import Training.Entities.Advertisement;
import Training.Entities.Container;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
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

    public static void sendXml(long [] ids, AdvDAO advDAO, HttpServletResponse response) throws Exception{
        Container container = new Container();
        for (long id :ids){
            container.add(advDAO.getById(id));
        }
        response.setContentType("application/download");
        response.addHeader("Content-Disposition","attachment;filename=export.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Container.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(container, os);
        byte[] content = os.toByteArray();
        response.addHeader("Content-Length", String.valueOf(content.length));
        FileCopyUtils.copy(content, response.getOutputStream());
    }

    public static File exportToXml(long [] ids, AdvDAO advDAO) {
        Container container = new Container();
        for (long id :ids){
            container.add(advDAO.getById(id));
        }
        try {
            File file = new File("tmp.txt");
            JAXBContext jaxbContext = JAXBContext.newInstance(Container.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(container, file);
            return file;
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return null;
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
