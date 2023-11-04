package danekerscode.api.domain.service.impl;

import danekerscode.api.domain.payload.request.EmailConfirmationRequest;
import danekerscode.api.domain.payload.response.StatusResponse;
import danekerscode.api.domain.service.AESService;
import danekerscode.api.common.exception.EmailRegisteredException;
import danekerscode.api.common.exception.EntityNotFoundException;
import danekerscode.api.domain.mapper.UserMapper;
import danekerscode.api.domain.model.User;
import danekerscode.api.domain.payload.request.MailSendRequest;
import danekerscode.api.domain.payload.request.UserRegistrationRequest;
import danekerscode.api.domain.repository.UserRepository;
import danekerscode.api.domain.service.MailService;
import danekerscode.api.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.function.Supplier;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MailService mailService;
    private final AESService aesService;

    @Override
    public User register(UserRegistrationRequest req) {
        var email = req.email();

        var optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            throw new EmailRegisteredException(email);
        }

        var userOtp = otp.get();
        mailService.send(new MailSendRequest(
                req.email(),
                userOtp
        ));

        var user = userMapper.toModel(email, userOtp);

        return userRepository.save(user);
    }

    @Override
    public StatusResponse confirmEmail(
            EmailConfirmationRequest req
    ) {
        var user = findByEmail(req.email());
        if (!user.getOtp().equals(req.otp())) {
            return StatusResponse.fail("invalid otp");
        }

        user.setOtp(null);
        user.setEmailVerified(true);

        userRepository.save(user);
        return StatusResponse
                .success(aesService.encrypt(user.getEmail()));
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "email", email));
    }

    private final Random random = new Random();
    private final Supplier<Integer> otp = () -> random.nextInt(100_000, 999_999);


}
