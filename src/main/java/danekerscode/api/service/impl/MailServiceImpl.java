package danekerscode.api.service.impl;

import danekerscode.api.payload.request.MailSendRequest;
import danekerscode.api.service.MailService;
import danekerscode.api.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final TemplateService templateService;
    @Value("${spring.mail.sender.address}")
    private String mailSenderAddress;

    @Override
    public void send(MailSendRequest mailSendRequest) {
        var mailSendingThread = new Thread(
                () -> {
                    try {
                        var msg = mailSender.createMimeMessage();
                        var helper = new MimeMessageHelper(msg, true, "UTF-8");

                        helper.setTo(mailSendRequest.to());
                        helper.setFrom(mailSenderAddress);
                        helper.setSubject("Email confirmation");
                        helper.setText(templateService.getTemplate(
                                        "email_confirmation",
                                        Map.of(
                                                "email", mailSendRequest.to(),
                                                "confirmationLink", mailSendRequest.confirmationLink()
                                        )
                                ),
                                true
                        );

                        templateService.addInlineCommonImages(helper);

                        mailSender.send(msg);
                        log.info("email send: {}", mailSendRequest.to());
                    } catch (Exception e) {
                        log.error("error during send mail message, {}", e.getMessage());
                    }
                }
        );

        mailSendingThread.setDaemon(true);
        mailSendingThread.start();
    }

}
