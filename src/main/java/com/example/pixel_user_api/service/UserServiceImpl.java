package com.example.pixel_user_api.service;

import com.example.pixel_user_api.data.dto.request.UserRequestDto;
import com.example.pixel_user_api.data.dto.response.UserResponseDto;
import com.example.pixel_user_api.data.entity.EmailData;
import com.example.pixel_user_api.data.entity.PhoneData;
import com.example.pixel_user_api.data.entity.User;
import com.example.pixel_user_api.mapper.EmailMapper;
import com.example.pixel_user_api.mapper.PhoneMapper;
import com.example.pixel_user_api.mapper.UserMapper;
import com.example.pixel_user_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final EmailMapper emailMapper;
    private final PhoneMapper phoneMapper;

    @Override
    @Transactional(readOnly = false)
    public UserResponseDto update(UserRequestDto userRequestDto) {
        User user = loadUser(userRequestDto.getId());
        updateUser(user, userRequestDto);
        User updatedUser = repository.save(user);
        return mapper.toResponseDto(updatedUser);
    }

    private User loadUser(Long userId) {
        if (userId == null) throw new EntityNotFoundException("Update request has no id");
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isEmpty()) throw new EntityNotFoundException("No such user");
        User user = userOptional.get();
        return user;
    }

    private void updateUser(User user, UserRequestDto userRequestDto) {
        updateUserEmails(user, userRequestDto);
        updateUserPhones(user, userRequestDto);
    }

    private void updateUserEmails(User user, UserRequestDto userRequestDto) {
        List<EmailData> userEmailData = user.getEmailData();
        List<String> requestEmails = userRequestDto.getEmailData();
        removeStaleEmails(userEmailData, requestEmails);
        addNewEmails(user, requestEmails);
    }

    private void removeStaleEmails(List<EmailData> userEmailData, List<String> requestEmails) {
        if (requestEmails == null) return;
        Predicate<EmailData> notContainedInRequestEmails = ed -> !requestEmails.contains(ed.getEmail());
        userEmailData.removeIf(notContainedInRequestEmails);
    }

    private void addNewEmails(User user, List<String> requestEmails) {
        if (requestEmails == null) return;
        List<EmailData> userEmailData = user.getEmailData();
        requestEmails.stream()
                .filter(em -> isNewEmail(userEmailData, em))
                .map(em -> emailMapper.toEmailData(em, user))
                .forEach(userEmailData::add);
    }

    private boolean isNewEmail(List<EmailData> userEmailData, String requestEmail) {
        boolean isNewEmail = userEmailData.stream()
                .map(EmailData::getEmail)
                .noneMatch(em -> em.equals(requestEmail));
        return isNewEmail;
    }

    private void updateUserPhones(User user, UserRequestDto userRequestDto) {
        List<PhoneData> userPhoneData = user.getPhoneData();
        List<String> requestPhones = userRequestDto.getPhoneData();
        removeStalePhones(userPhoneData, requestPhones);
        addNewPhones(user, requestPhones);
    }

    private void removeStalePhones(List<PhoneData> userEmailData, List<String> requestPhones) {
        if (requestPhones == null) return;
        Predicate<PhoneData> notContainedInRequestPhones = ed -> !requestPhones.contains(ed.getPhone());
        userEmailData.removeIf(notContainedInRequestPhones);
    }

    private void addNewPhones(User user, List<String> requestPhones) {
        if (requestPhones == null) return;
        List<PhoneData> userPhoneData = user.getPhoneData();
        requestPhones.stream()
                .filter(p -> isNewPhone(userPhoneData, p))
                .map(p -> phoneMapper.toPhoneData(p, user))
                .forEach(userPhoneData::add);
    }

    private boolean isNewPhone(List<PhoneData> userPhoneData, String requestPhone) {
        boolean isNewPhone = userPhoneData.stream()
                .map(PhoneData::getPhone)
                .noneMatch(p -> p.equals(requestPhone));
        return isNewPhone;
    }
}
