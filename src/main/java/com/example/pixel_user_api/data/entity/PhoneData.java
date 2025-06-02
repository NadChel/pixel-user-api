package com.example.pixel_user_api.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "phone_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PhoneData {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @Pattern(regexp = "\\d+", message = "Phone number must contain digits only")
    private String phone;

    public static PhoneData of(String phone, User user) {
        PhoneData phoneData = new PhoneData();
        phoneData.setPhone(phone);
        phoneData.setUser(user);
        return phoneData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneData phoneData)) return false;
        return Objects.equals(phone, phoneData.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(phone);
    }
}
