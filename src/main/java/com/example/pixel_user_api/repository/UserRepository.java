package com.example.pixel_user_api.repository;

import com.example.pixel_user_api.data.entity.User;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {
    User save(User user);
}
