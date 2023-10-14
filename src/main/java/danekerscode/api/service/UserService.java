package danekerscode.api.service;

import danekerscode.api.model.User;
import danekerscode.api.payload.request.EmailConfirmationRequest;
import danekerscode.api.payload.request.UserRegistrationRequest;
import danekerscode.api.payload.response.StatusResponse;

public interface UserService {

    User register(UserRegistrationRequest req);

    StatusResponse confirmEmail(EmailConfirmationRequest req);
}
