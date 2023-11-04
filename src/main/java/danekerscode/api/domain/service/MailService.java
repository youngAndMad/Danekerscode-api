package danekerscode.api.domain.service;

import danekerscode.api.domain.payload.request.MailSendRequest;

/**
 * This interface provides methods for sending emails.
 */
public interface MailService {

    /**
     * Sends an email using the given mail send request.
     *
     * @param mailSendRequest the mail send request containing the email details
     */
    void send(MailSendRequest mailSendRequest);

}