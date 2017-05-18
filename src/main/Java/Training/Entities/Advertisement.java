package Training.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by Администратор on 25.04.2017.
 */
@Entity
@Table(name="Advs")
@XmlRootElement(name = "advertisement")
public class Advertisement {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @Column(name="short_desc")
    private String shortDesc;

    @Column(name="long_desc")
    private String longDesc;

    private String phone;
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "photo_id")
    private Photo photo;

    @Column(name="in_basket")
    private boolean inBasket;

    public Advertisement(){};

    public Advertisement(String name, String shortDesc, String longDesc, String phone, double price, Photo photo) {
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.phone = phone;
        this.price = price;
        this.photo = photo;
    }

    @XmlTransient
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    @XmlElement
    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    @XmlElement
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @XmlTransient
    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @XmlTransient
    public Boolean isInBasket() {
        return inBasket;
    }

    public void setInBasket(boolean inBasket) {
        this.inBasket = inBasket;
    }
}
