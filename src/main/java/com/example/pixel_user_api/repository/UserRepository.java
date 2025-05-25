package com.example.pixel_user_api.repository;

import com.example.pixel_user_api.data.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {

    Optional<User> findById(Long id);

    @Query("""
            SELECT u
            FROM User u
            WHERE (CAST(:dateOfBirth AS DATE) IS NULL OR u.dateOfBirth > CAST(:dateOfBirth AS DATE))
            AND (:name IS NULL OR u.name LIKE CONCAT(:name, '%'))
            AND (:email IS NULL OR EXISTS (
                SELECT 1 FROM EmailData ed WHERE ed.user = u AND ed.email = :email
            ))
            AND (:phone IS NULL OR EXISTS (
                SELECT 1 FROM PhoneData pd WHERE pd.user = u AND pd.phone = :phone
            ))
            """)
    List<User> find(@Param("dateOfBirth") LocalDate dateOfBirth,
                    @Param("phone") String phone,
                    @Param("name") String name,
                    @Param("email") String email,
                    Pageable pageable);

    User save(User user);
}
