package com.github.ChuprinaVlad;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionProvider {

//    final public String username = "testvlad.123456@gmail.com";
//    final public String password = "bxohylduhunadzbl";

    public Properties prop = new Properties();

    {
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    public Session getSession() {
        return Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getenv("username"), System.getenv("password"));
            }
        });
    }
}
