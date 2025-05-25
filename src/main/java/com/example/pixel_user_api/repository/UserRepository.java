package com.example.pixel_user_api.repository;

import com.example.pixel_user_api.data.dto.request.FindUserRequestDto;
import com.example.pixel_user_api.data.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {

    Optional<User> findById(Long id);

    @Query("""
            SELECT u
            FROM User u
            WHERE (CAST(:#{#dto.getDateOfBirth()} AS DATE) IS NULL OR u.dateOfBirth > CAST(:#{#dto.getDateOfBirth()} AS DATE))
            AND (:#{#dto.getName()} IS NULL OR u.name LIKE CONCAT(:#{#dto.getName()}, '%'))
            AND (:#{#dto.getEmail()} IS NULL OR EXISTS (
                SELECT 1 FROM EmailData ed WHERE ed.user = u AND ed.email = :#{#dto.getEmail()}
            ))
            AND (:#{#dto.getPhone()} IS NULL OR EXISTS (
                SELECT 1 FROM PhoneData pd WHERE pd.user = u AND pd.phone = :#{#dto.getPhone()}
            ))
            """)
    List<User> find(FindUserRequestDto dto,
                    Pageable pageable);

    User save(User user);
}
