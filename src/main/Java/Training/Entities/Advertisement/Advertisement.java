package Training.Entities.Advertisement;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

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

    @Column(name="creation_date")
    private Date creationDate;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="creater_id")
//    private SmallUserEntity smallUserEntity;

    @Column(name="creater_username")
    private String createrUserName;

    public Advertisement(){};

    public Advertisement(String name, String shortDesc, String longDesc, String phone, double price, Photo photo, boolean inBasket, Date creationDate, String createrUserName) {
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.phone = phone;
        this.price = price;
        this.photo = photo;
        this.inBasket = inBasket;
        this.creationDate = creationDate;
        this.createrUserName = createrUserName;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

//    @XmlTransient
//    public SmallUserEntity getSmallUserEntity() {
//        return smallUserEntity;
//    }
//
//    public void setSmallUserEntity(SmallUserEntity smallUserEntity) {
//        this.smallUserEntity = smallUserEntity;
//    }


    public String getCreaterUserName() {
        return createrUserName;
    }

    public void setCreaterUserName(String createrUserName) {
        this.createrUserName = createrUserName;
    }
}
