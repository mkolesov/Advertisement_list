package Training.Entities.SecurityGroups;

import Training.Entities.User.ExtendedUserEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Администратор on 27.06.2017.
 */

@Entity
@Table(name = "user_groups")
public class GroupEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "adv_maximum")
    private int advMaximumCount;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(/*mappedBy = "groups", */fetch = FetchType.LAZY)
    @JoinTable(
            name="groups_members",
            joinColumns=@JoinColumn(name="group_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="user_name", referencedColumnName="user_name"))
    private List<ExtendedUserEntity> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdvMaximumCount() {
        return advMaximumCount;
    }

    public void setAdvMaximumCount(int advMaximumCount) {
        this.advMaximumCount = advMaximumCount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<ExtendedUserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<ExtendedUserEntity> users) {
        this.users = users;
    }
}
