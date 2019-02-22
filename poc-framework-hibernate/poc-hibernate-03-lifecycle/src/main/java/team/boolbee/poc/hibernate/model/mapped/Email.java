package team.boolbee.poc.hibernate.model.mapped;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "Email2")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String subject;
    @OneToOne(mappedBy = "email")
    Message message;

    public Email() {
    }

    public Email(String subject) {
        setSubject(subject);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        // note use of message.content because otherwise properly constructed
        // relationships would cause an endless loop that never ends
        // and therefore runs endlessly.
        return "Email{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", message.content=" + (message != null ? message.getContent() : "null") +
                '}';
    }
}

