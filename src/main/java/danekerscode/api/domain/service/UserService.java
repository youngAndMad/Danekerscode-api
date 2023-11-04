package danekerscode.api.domain.service;

import danekerscode.api.domain.model.User;
import danekerscode.api.domain.payload.request.EmailConfirmationRequest;
import danekerscode.api.domain.payload.request.UserRegistrationRequest;
import danekerscode.api.domain.payload.response.StatusResponse;

/**
 * This interface provides methods for user registration and email confirmation.
 */
public interface UserService {

    /**
     * Registers a new user with the given registration request.
     *
     * @param req the user registration request containing the user details
     * @return the registered user
     */
    User register(UserRegistrationRequest req);

    /**
     * Confirms the email address of a user with the given confirmation request.
     *
     * @param req the email confirmation request containing the email and OTP
     * @return the status response indicating the success or failure of the email confirmation
     */
    StatusResponse confirmEmail(EmailConfirmationRequest req);
}