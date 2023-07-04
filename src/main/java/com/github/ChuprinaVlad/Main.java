package com.github.ChuprinaVlad;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

// TODO: remove this class after refactoring
public class Main {

    // TODO: handle exceptions with try-catch. Use custom exceptions - https://www.baeldung.com/java-new-custom-exception
    //  extend from RuntimeException, not from Exception as in guide
    public static void main(String[] args) throws MessagingException, IOException, URISyntaxException {
        final String username = "testvlad.123456@gmail.com";

        // TODO: add as environment variable in 'Edit Configurations', read using
        //  String password = System.getenv().getOrDefault("EMAIL_PASSWORD", "");
        final String password = "bxohylduhunadzbl";

        final String [] emails = new String[] {"kolegran@gmail.com", "kolegran+1@gmail.com", "kolegran+2@gmail.com"};
        String email, msg = null;

        // TODO: move properties and session to SessionProvider
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // TODO: move emails sending logic to EmailSender
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));

        for (int i = 0; emails.length > i; i++) {
            email = emails[i];
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Mail Subject");

        switch (email) {
            case "kolegran@gmail.com":
                msg = "Hi John Doe, this is my first email using JavaMailer";
                break;
            case "kolegran+1@gmail.com":
                msg = "Hi Sam Baker, this is my first email using JavaMailer";
                break;
            case "kolegran+2@gmail.com":
                msg = "Hi Alex Rider, this is my first email using JavaMailer";
                break;
            default:
                System.out.println("Mail entered incorrectly");
                break;
        }
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            var res = Main.class.getClassLoader().getResource("smile.jpg");

            if (res != null) {
                var file = new File(res.toURI()); // TODO: var best practices
                attachmentBodyPart.attachFile(file);
                multipart.addBodyPart(attachmentBodyPart);

                message.setContent(multipart);

                Transport.send(message);
            }
        }
    }
}