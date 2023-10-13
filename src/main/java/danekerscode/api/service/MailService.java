package danekerscode.api.service;

import danekerscode.api.payload.request.MailSendRequest;

public interface MailService {

    Void send(MailSendRequest mailSendRequest);

}
