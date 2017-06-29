package Training.Entities.User;

import Training.Entities.SecurityGroups.GroupEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Администратор on 23.06.2017.
 */
@Entity
@Table(name="users")
public class ExtendedUserEntity extends UserEntityBase implements Serializable {

    //@ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="groups_members",
            joinColumns=@JoinColumn(name="user_name", referencedColumnName="user_name"),
            inverseJoinColumns=@JoinColumn(name="group_id", referencedColumnName="id"))
    private List<GroupEntity> groups;

    @Column(name = "adv_count")
    private int advCount;

    public int getAdvCount() {
        return advCount;
    }

    public void setAdvCount(int advCount) {
        this.advCount = advCount;
    }

    public List<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
    }
}
