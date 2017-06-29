package Training.Entities.Advertisement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 15.05.2017.
 */
@XmlRootElement(name = "container")
public class Container {

    @XmlElement(name = "advertisement")
    private List<Advertisement> advertisements = new ArrayList<Advertisement>();

    public void add(Advertisement advertisement){
        advertisements.add(advertisement);
    }

    public List<Advertisement> getAdvertisements(){
        return advertisements;
    }
}
