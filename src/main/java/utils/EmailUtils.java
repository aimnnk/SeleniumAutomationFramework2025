package utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

	public static void sendTestReport(String reportPath) {

		final String senderEmail = "your email";
		final String appPassword = "app password email";
		final String recipientEmail = "your email";

		// SMTP server properties
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.port", "587");

		// create a session with authentication
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, appPassword);
			}
		});
		session.setDebug(true);

		try {
			// create email message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Test Email From QA Automation");
//					message.setText("Hello \n\nThis is a test email from Java \n\nRegard, \nQA Team ");

			// email body part
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Hello \n\nThis is a test email from Java \n\nRegard, \nQA Team ");

			// attachment part
			MimeBodyPart attachmentPart = new MimeBodyPart();
//			String filePath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
			System.out.println("Attachemnt path is - " + reportPath);
			attachmentPart.attachFile(new File(reportPath));

			// combine body and attachement part
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);

			// send email
			Transport.send(message);
			System.out.println("Email sent successfully ***");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
