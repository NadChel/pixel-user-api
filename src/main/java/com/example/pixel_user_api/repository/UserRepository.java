package com.example.pixel_user_api.repository;

import com.example.pixel_user_api.data.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {

    Optional<User> findById(Long id);

    List<User> findAll(Specification<User> specification, Pageable pageable);

    User save(User user);
}
