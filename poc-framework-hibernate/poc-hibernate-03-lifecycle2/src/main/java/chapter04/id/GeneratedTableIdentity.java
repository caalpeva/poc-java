package chapter04.id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GeneratedTableIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @Column
    String value;

    public GeneratedTableIdentity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
