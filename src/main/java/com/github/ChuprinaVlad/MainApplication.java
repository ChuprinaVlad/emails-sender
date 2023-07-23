package com.github.ChuprinaVlad;

import javax.mail.MessagingException;

public class MainApplication {

    // dependency injection
    public static void main(String[] args) throws MessagingException {
        SessionProvider sessionProvider = new SessionProvider();
        UserDataCsvParser userDataCsvParser = new UserDataCsvParser();

        EmailSender emailSender = new EmailSender(sessionProvider, userDataCsvParser);
        emailSender.send();
    }
}
