package danekerscode.api.service;

import danekerscode.api.payload.request.MailSendRequest;

public interface MailService {

    void send(MailSendRequest mailSendRequest);

}
