package concur.core.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer cnt;

    public User(String name, Integer cnt) {
        this.name = name;
        this.cnt = cnt;
    }

    public User() {

    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
}
