package com.github.ChuprinaVlad;

import javax.mail.Session;
import java.util.List;

public class EmailSender {

    private final SessionProvider sessionProvider;
    private final UserDataCsvParser userDataCsvParser;

    public EmailSender(SessionProvider sessionProvider, UserDataCsvParser userDataCsvParser) {
        this.sessionProvider = sessionProvider;
        this.userDataCsvParser = userDataCsvParser;
    }

    public void send() {
        Session session = sessionProvider.getSession();
        List<UserData> userData = userDataCsvParser.parse();

        for (UserData data : userData) {
            String message = "Hi" + data.getName() + ", this is my first email using JavaMailer";
            String email = data.getEmail();

            // send email logic
        }
    }
}
