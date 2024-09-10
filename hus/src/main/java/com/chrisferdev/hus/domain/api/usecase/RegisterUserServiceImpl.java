package com.chrisferdev.hus.domain.api.usecase;

import com.chrisferdev.hus.configuration.exception.BirthDathException;
import com.chrisferdev.hus.configuration.exception.DocumentException;
import com.chrisferdev.hus.configuration.exception.EmailException;
import com.chrisferdev.hus.configuration.exception.PhoneException;
import com.chrisferdev.hus.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import com.chrisferdev.hus.domain.model.UserType;
import com.chrisferdev.hus.domain.spi.output.IRegisterUserPersistencePort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

@Service
public class RegisterUserServiceImpl {

    private final IRegisterUserPersistencePort iRegisterUserPersistencePort;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_REGEX = "^\\+?\\d{1,13}$";
    private static final String DOCUMENT_REGEX = "\\d+";

    public RegisterUserServiceImpl(IRegisterUserPersistencePort iRegisterUserPersistencePort, BCryptPasswordEncoder passwordEncoder) {
        this.iRegisterUserPersistencePort = iRegisterUserPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRequest saveUser(UserRequest userRequest) {
        validateUserRequest(userRequest);

        if (userRequest.getUserType() == null) {
            userRequest.setUserType(UserType.WAREHOUSE_ASSISTANT);
        }

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return iRegisterUserPersistencePort.saveUser(userRequest);
    }

    private void validateUserRequest(UserRequest userRequest) {
        validateEmail(userRequest.getEmail());
        validatePhone(userRequest.getPhoneNumber());
        validateDocumentId(userRequest.getDocumentId());
        validateBirthDate(userRequest.getBirthDate());
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new EmailException(ExceptionResponse.ERROR_EMAIL.getMessage());
        }

        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new EmailException(ExceptionResponse.ERROR_EMAIL.getMessage());
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new PhoneException(ExceptionResponse.ERROR_PHONE.getMessage());
        }

        if (!Pattern.matches(PHONE_REGEX, phone)) {
            throw new PhoneException(ExceptionResponse.ERROR_PHONE.getMessage());
        }
    }

    private void validateDocumentId(String documentId) {
        if (documentId == null || documentId.trim().isEmpty()) {
            throw new DocumentException(ExceptionResponse.ERROR_DOCUMENT_ID.getMessage());
        }

        if (!Pattern.matches(DOCUMENT_REGEX, documentId)) {
            throw new DocumentException(ExceptionResponse.ERROR_DOCUMENT_ID.getMessage());
        }
    }

    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new BirthDathException(ExceptionResponse.ERROR_BIRTHDATE.getMessage());
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 18) {
            throw new BirthDathException(ExceptionResponse.ERROR_BIRTHDATE.getMessage());
        }
    }
}
