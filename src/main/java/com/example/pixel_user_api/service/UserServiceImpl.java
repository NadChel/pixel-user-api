package com.example.pixel_user_api.service;

import com.example.pixel_user_api.data.dto.request.FindUserRequestDto;
import com.example.pixel_user_api.data.dto.request.UpdateUserRequestDto;
import com.example.pixel_user_api.data.dto.response.UserResponseDto;
import com.example.pixel_user_api.data.entity.User;
import com.example.pixel_user_api.mapper.UserMapper;
import com.example.pixel_user_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = false)
    public UserResponseDto update(long id, UpdateUserRequestDto userRequestDto) {
        User user = loadUser(id);
        userMapper.updateUser(user, userRequestDto);
        User updatedUser = repository.save(user);
        UserResponseDto userResponseDto = userMapper.toResponseDto(updatedUser);
        return userResponseDto;
    }

    private User loadUser(Long userId) {
        if (userId == null) throw new EntityNotFoundException("No user id is passed in");
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isEmpty()) throw new EntityNotFoundException("No such user");
        User user = userOptional.get();
        return user;
    }

    @Override
    public List<UserResponseDto> find(FindUserRequestDto userRequestDto, Pageable pageable) {
        Specification<User> specification = buildSpecification(userRequestDto);
        List<User> users = repository.findAll(specification, pageable);
        List<UserResponseDto> userDtos = users.stream()
                .map(userMapper::toResponseDto)
                .toList();
        return userDtos;
    }

    private static Specification<User> buildSpecification(FindUserRequestDto userRequestDto) {
        return (root, query, criteriaBuilder) -> {
            var nameBeginsWith = criteriaBuilder.like(root.get("name"), userRequestDto.getName() + "%");
            var bornAfter = criteriaBuilder.greaterThan(root.get("dateOfBirth"), userRequestDto.getDateOfBirth());
            root.fetch("emailData", JoinType.LEFT);
            var hasEmail = criteriaBuilder.equal(root.join("emailData", JoinType.LEFT).get("email"), userRequestDto.getEmail());
            var hasPhone = criteriaBuilder.equal(root.join("phoneData", JoinType.LEFT).get("phone"), userRequestDto.getPhone());

            List<Predicate> predicates = new ArrayList<>();
            if (userRequestDto.getName() != null) predicates.add(nameBeginsWith);
            if (userRequestDto.getDateOfBirth() != null) predicates.add(bornAfter);
            if (userRequestDto.getEmail() != null) predicates.add(hasEmail);
            if (userRequestDto.getPhone() != null) predicates.add(hasPhone);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
