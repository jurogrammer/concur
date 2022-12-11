package concur.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class Reservation {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public Reservation(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
