package com.github.ChuprinaVlad;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class EmailSender {

    private final SessionProvider sessionProvider;
    private final UserDataCsvParser userDataCsvParser;

    public EmailSender(SessionProvider sessionProvider, UserDataCsvParser userDataCsvParser) {
        this.sessionProvider = sessionProvider;
        this.userDataCsvParser = userDataCsvParser;
    }

    public void send() throws MessagingException {
        Session session = sessionProvider.getSession();
        List<UserData> userData = userDataCsvParser.parse();

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("testvlad.123456@gmail.com"));
        } catch (MessagingException e) {
            throw new RuntimeException("Incorrect user email ",e);
        }

        for (UserData data : userData) {
            String msg = "Hi" + data.getName() + ", this is my first email using JavaMailer";
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(data.getEmail()));

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            try {
                mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
            } catch (MessagingException e) {
                throw new RuntimeException("Incorrect  type of the object ",e);
            }

            Multipart multipart = new MimeMultipart();
            try {
                multipart.addBodyPart(mimeBodyPart);
            } catch (MessagingException e) {
                throw new RuntimeException("Error add BodyPart ",e);
            }

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            URL res = getClass().getClassLoader().getResource("smile.jpg");

            if (res != null) {
                File file = null; // TODO: var best practices
                try {
                    file = new File(res.toURI());
                } catch (URISyntaxException e) {
                    throw new RuntimeException("URL adress can not be converted to a URI",e);
                }
                try {
                    attachmentBodyPart.attachFile(file);
                } catch (IOException e) {
                    throw new RuntimeException("errors related to accessing the file",e); // - помилки, пов’язані з доступом до файлу
                } catch (MessagingException e) {
                    throw new RuntimeException("message related errors",e); // - помилки, пов’язані з повідомленням
                }
                try {
                    multipart.addBodyPart(attachmentBodyPart);
                } catch (MessagingException e) {
                    throw new RuntimeException("Error add attachment",e);
                }

                try {
                    message.setContent(multipart);
                } catch (MessagingException e) {
                    throw new RuntimeException("Error encoding multipart",e);
                }

                try {
                    Transport.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException("Not be sent message",e);
                }
            }
        }
    }
}
