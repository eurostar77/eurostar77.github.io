package com.bueffeltier.logic.foundation;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService
{
	private static EmailService instance;

	private EmailService()
	{

	}

	public static EmailService getInstance()
	{
		if (instance == null)
		{
			instance = new EmailService();
		}
		return instance;
	}

	public boolean send(
			String to,
			String subj,
			String msg,
			final String user,
			final String pass
	)
	{
		Properties props = new Properties();

		// TODO sveng 03.12.2022: systemeinstellungen
//		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		// below mentioned mail.smtp.port is optional
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session
				.getInstance(props, new javax.mail.Authenticator()
				{
					@Override
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(user, pass);
					}
				});

		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(
					Message.RecipientType.TO, new InternetAddress(to)
			);
			message.setSubject(subj);
			message.setText(msg);

			// todo: löschen, wenn e-mail funktioniert:
			System.out.println(message);

			Transport.send(message);

			return true;

		} catch (Exception e)
		{
			e.printStackTrace();

			return false;
		}
	}
}
