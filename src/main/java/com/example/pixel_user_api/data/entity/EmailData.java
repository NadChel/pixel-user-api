package com.example.pixel_user_api.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "email_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmailData {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @Email(message = "Email must be valid")
    private String email;

    public static EmailData of(String email, User user) {
        EmailData emailData = new EmailData();
        emailData.setEmail(email);
        emailData.setUser(user);
        return emailData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailData emailData)) return false;
        return Objects.equals(email, emailData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
