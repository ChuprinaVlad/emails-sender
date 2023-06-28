package com.github.ChuprinaVlad;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws MessagingException, IOException {

        final String  username = "testvlad.123456@gmail.com";
        final String password = "bxohylduhunadzbl";
        final String EMAILS_CSV = "emails.csv";

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

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("testvlad.123456@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("kolegran@gmail.com"));
        message.setSubject("Mail Subject");

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(EMAILS_CSV);
        System.out.println(inputStream);

        String msg = "This is my second email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);


        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(new File("smile.jpg"));
        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

  //      Transport.send(message);
    }
}