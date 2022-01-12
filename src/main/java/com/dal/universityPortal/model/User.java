package com.dal.universityPortal.model;

import com.dal.universityPortal.service.ModelValidatorService;
import com.dal.universityPortal.validator.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.dal.universityPortal.constant.UserConstant.PASSWORD_MIN_LENGTH;
import static com.dal.universityPortal.constant.UserConstant.USERNAME_MIN_LENGTH;

@Component
public class User extends ValidatedModel{

    private Integer id;
    private String username;
    private String email;
    private String password;
    private UserType type;
    private UserStatus status;
    private Integer resetCode;

    public User() {
        super(new ModelValidatorService());
    }

    public User(String username, String email, String password, UserType type) {
        super(new ModelValidatorService());
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User(Map<String, Object> userRow) {
        super(new ModelValidatorService());
        this.id = (Integer) userRow.get("id");
        this.username = (String) userRow.get("username");
        this.email = (String) userRow.get("email");
        this.password = (String) userRow.get("password");
        this.type = UserType.valueOf((String) userRow.get("type"));
        this.status = UserStatus.valueOf((String) userRow.get("status"));
        this.resetCode = (Integer) userRow.get("reset_code");
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type == null ? null : type.toString();
    }

    public UserType getTypeEnum() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatusString() {
        return status == null ? null : status.toString();
    }

    public UserStatus getStatus() {
        return status;
    }

    public Integer getResetCode() {
        return resetCode;
    }

    public void setId(Integer id) {this.id = id; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(String type) {
        this.type = UserType.valueOf(type.toUpperCase());
    }

    public void setTypeEnum(UserType type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public List<FieldValidator> getFieldValidatorMapping() {
        List<FieldValidator> fieldValidators = new ArrayList<>();
        List<Validator<String>> passwordValidators = new ArrayList<>();
        passwordValidators.add(new MinLengthValidator(PASSWORD_MIN_LENGTH));
        passwordValidators.add(new UpperCasePresent());
        passwordValidators.add(new SpecialCharacterPresentValidator());
        fieldValidators.add(new FieldValidator<>("username",
                username, new MinLengthValidator(USERNAME_MIN_LENGTH)));
        fieldValidators.add(new FieldValidator<>("password",
                password, new ChainedValidator<>(passwordValidators)));
        return fieldValidators;
    }

}
