package bo.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

public class Mailer {
    private static Mailer mailer;
    private Session session;
    private static final String ENE_SENDER_EMAIL = System.getenv("ENE_SENDER_EMAIL");
    private static final String ENE_SENDER_PASSWORD = System.getenv("ENE_SENDER_PASSWORD");
    private static final String EMAIL_HOST = "smtp.gmail.com";
    private Mailer() {
        this.setupServerProperties();
    }

    public void sendEmail(MimeMessage mimeMessage) throws MessagingException {
        Transport transport = session.getTransport("smtp");
        transport.connect(EMAIL_HOST, ENE_SENDER_EMAIL, ENE_SENDER_PASSWORD);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }

    public MimeMessage draftEmail(String recepient, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        mimeMessage.setSubject(subject);

        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(body, "utf-8", "html");
        multipart.addBodyPart(bodyPart);
        mimeMessage.setContent(multipart);
        mimeMessage.setFrom(new InternetAddress(ENE_SENDER_EMAIL));

        return mimeMessage;
    }

    private void setupServerProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", EMAIL_HOST);
        properties.put("mail.smtp.port", "587");
        session = Session.getDefaultInstance(properties, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ENE_SENDER_EMAIL, ENE_SENDER_PASSWORD);
            }
        });
    }

    public static Mailer getInstance() {
        return mailer == null ? (mailer = new Mailer()) : mailer;
    }
}
