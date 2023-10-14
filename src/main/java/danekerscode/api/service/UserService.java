package danekerscode.api.service;

import danekerscode.api.model.User;
import danekerscode.api.payload.request.UserRegistrationRequest;

public interface UserService {

    User register(UserRegistrationRequest req);

}
