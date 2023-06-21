package com.github.ChuprinaVlad;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws MessagingException, IOException {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return super.getPasswordAuthentication();
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("testvlad.123456@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("kolegran@gmail.com"));
        message.setSubject("Mail Subject");

        String msg = "This is my first email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(new File("C:/Users/Влад/IdeaProjects/emails-sender/src/main/" +
                "java/com/github/ChuprinaVlad/smile.jpg"));
        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}