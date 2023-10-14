package danekerscode.api.service.impl;

import danekerscode.api.exception.EmailRegisteredException;
import danekerscode.api.mapper.UserMapper;
import danekerscode.api.model.User;
import danekerscode.api.payload.request.UserRegistrationRequest;
import danekerscode.api.repository.UserRepository;
import danekerscode.api.service.AESService;
import danekerscode.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AESService aesService;

    @Override
    public User register(UserRegistrationRequest req) {
        var email = req.email();

        var optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            throw new EmailRegisteredException(email);
        } else {

            var apiKey = aesService.encrypt(email);
            var user = userMapper.toModel(email,apiKey);

            return userRepository.save(user);
        }

    }


}
