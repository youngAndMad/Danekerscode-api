package danekerscode.api.service;

import danekerscode.api.annotation.ValidatedMethod;
import danekerscode.api.payload.request.MailSendRequest;

public interface MailService {

    @ValidatedMethod
    void send(MailSendRequest mailSendRequest);

}
