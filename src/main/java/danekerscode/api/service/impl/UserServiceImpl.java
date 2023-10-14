package danekerscode.api.service.impl;

import danekerscode.api.exception.EmailRegisteredException;
import danekerscode.api.exception.EntityNotFoundException;
import danekerscode.api.mapper.UserMapper;
import danekerscode.api.model.User;
import danekerscode.api.payload.request.EmailConfirmationRequest;
import danekerscode.api.payload.request.MailSendRequest;
import danekerscode.api.payload.request.UserRegistrationRequest;
import danekerscode.api.payload.response.StatusResponse;
import danekerscode.api.repository.UserRepository;
import danekerscode.api.service.AESService;
import danekerscode.api.service.MailService;
import danekerscode.api.service.UserService;
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
        } else {

            mailService.send(new MailSendRequest(
                    req.email(),
                    otp.get()
            ));

            var user = userMapper.toModel(email, otp.get());
            return userRepository.save(user);
        }

    }

    @Override
    public StatusResponse confirmEmail(
            EmailConfirmationRequest req
    ) {
        var user = findByEmail(req.email());
        if (!user.getOtp().equals(req.otp())){
            return StatusResponse.fail("invalid otp");
        }

        user.setOtp(null);
        user.setEmailVerified(true);

        userRepository.save(user);
        return StatusResponse.success(aesService.encrypt(user.getEmail()));
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "email", email));
    }

    private final Random random = new Random();
    private final Supplier<Integer> otp = () -> random.nextInt(100_000, 999_999);


}
