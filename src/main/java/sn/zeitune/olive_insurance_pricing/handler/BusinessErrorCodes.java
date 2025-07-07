package sn.zeitune.olive_insurance_pricing.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {

    NO_CODE(0, "No code", HttpStatus.NOT_IMPLEMENTED),
    INCORRECT_CURRENT_PASSWORD(300, "Incorrect current password", HttpStatus.BAD_REQUEST),
    NEW_PASSWORD_MISMATCH(301, "New password and confirm password do not match", HttpStatus.BAD_REQUEST),
    ACCOUNT_LOCKED(302, "User account is locked", HttpStatus.FORBIDDEN),
    ACCOUNT_DISABLED(303, "User account is disabled", HttpStatus.FORBIDDEN),
    BAD_CREDENTIALS(304, "Bad credentials", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND(305, "User not found", HttpStatus.NOT_FOUND),
    DUPLICATE_EMAIL(306, "Email already exists", HttpStatus.BAD_REQUEST),
    DUPLICATE_USERNAME(307, "Username already exists", HttpStatus.BAD_REQUEST),
    
    // Field Value errors
    FIELD_VALUE_NOT_FOUND(400, "Field value not found", HttpStatus.NOT_FOUND),
    FIELD_VALUE_ALREADY_EXISTS(401, "Field value already exists", HttpStatus.BAD_REQUEST),
    
    // Field errors
    FIELD_NOT_FOUND(410, "Field not found", HttpStatus.NOT_FOUND),
    FIELD_ALREADY_EXISTS(411, "Field already exists", HttpStatus.BAD_REQUEST),
    
    // Constant errors
    CONSTANT_NOT_FOUND(420, "Constant not found", HttpStatus.NOT_FOUND),
    CONSTANT_ALREADY_EXISTS(421, "Constant already exists", HttpStatus.BAD_REQUEST),
    
    // Formula errors
    FORMULA_NOT_FOUND(430, "Formula not found", HttpStatus.NOT_FOUND),
    FORMULA_ALREADY_EXISTS(431, "Formula already exists", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}