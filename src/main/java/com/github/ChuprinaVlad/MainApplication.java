package com.github.ChuprinaVlad;

public class MainApplication {

    // dependency injection
    public static void main(String[] args) {
        SessionProvider sessionProvider = new SessionProvider();
        UserDataCsvParser userDataCsvParser = new UserDataCsvParser();

        EmailSender emailSender = new EmailSender(sessionProvider, userDataCsvParser);
        emailSender.send();
    }
}
