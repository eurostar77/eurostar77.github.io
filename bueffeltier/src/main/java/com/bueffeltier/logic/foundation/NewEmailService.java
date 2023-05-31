package com.bueffeltier.logic.foundation;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static javax.mail.Message.RecipientType.TO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

/*
 * https://blog.sebastian-daschner.com/entries/sending-emails-gmail-api-java
 * https://github.com/sdaschner/java-playground/blob/gmail-api/src/main/java/com/sebastian_daschner/examples/GMailer.java
 * developers.google.com/workspace/guides/create-credentials
 * 
 * How to make it work for production? I
 * You can change the localhost receiver url in the code (IIRC that was documented somewhere) to make that work there (but then, also the extra port or URL has to be publicly reachable...), or you copy your credentials file to your production env (I've done that before), or you have access to the google admin console (I think you need a GSuite account) and can use the service account secrets (with domain-wide delegation). Sorry that the Google auth is a bit of a complex thing :)
 */
public class NewEmailService
{

	private static NewEmailService instance;

	private String senderEMail = "";

	private final Gmail service;

	// todo: app settings
	// https://console.cloud.google.com/apis/credentials?project=bueffeltier
//	private static final String googleMailOAuthClientId = "199777671630-00boj2hk5cg0r7t8lm0rkk6o6a3avv1e.apps.googleusercontent.com";
	private static final String googleMailOAuthClientId = "199777671630-8jnjpfc233093ovi1qn7nnp2d26klrps.apps.googleusercontent.com";

	private AppPropertyService appPropertyService = AppPropertyService
			.getInstance();

	private NewEmailService() throws Exception
	{
		senderEMail = appPropertyService.getGoogleEmailServiceEMailAddress();

		NetHttpTransport httpTransport = GoogleNetHttpTransport
				.newTrustedTransport();

		GsonFactory jsonFactory = GsonFactory.getDefaultInstance();

		service = new Gmail.Builder(
				httpTransport, jsonFactory,
				getCredentials(httpTransport, jsonFactory)
		).setApplicationName(

				appPropertyService.getGoogleEmailServiceApplicationName()

		).build();
	}

	public static NewEmailService getInstance() throws Exception
	{
		if (instance == null)
		{
			instance = new NewEmailService();
		}
		return instance;
	}

	private static Credential getCredentials(
			final NetHttpTransport httpTransport,
			GsonFactory jsonFactory
	) throws IOException
	{
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
				jsonFactory,
				new InputStreamReader(
						NewEmailService.class.getResourceAsStream(
								"client_secret_" + googleMailOAuthClientId
										+ ".json"
						)
				)
		);

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND)
		).setDataStoreFactory(
				// new FileDataStoreFactory(Paths.get("tokens").toFile())
				new FileDataStoreFactory(
						Paths.get("C:\\eclipse-workspace\\bueffeltier\\tokens")
								.toFile()
				)
		).setAccessType("offline").build();

		LocalServerReceiver receiver = new LocalServerReceiver.Builder()
				.setPort(8888).build();

		return new AuthorizationCodeInstalledApp(flow, receiver)
				.authorize("user");
	}

	public void sendMail(String subject, String message, String receiverEMail)
			throws Exception
	{
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		// TODO sveng 02.02.2023: Send HTML Template: For the MIME message you
		// can set html / multipart and then add your HTML content. Java Text
		// blocks help here or templating approaches like Quarkus Qute
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(senderEMail));
		email.addRecipient(TO, new InternetAddress(receiverEMail));
		email.setSubject(subject);
		email.setText(message);

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		email.writeTo(buffer);

		byte[] rawMessageBytes = buffer.toByteArray();

		String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);

		Message msg = new Message();
		msg.setRaw(encodedEmail);

		try
		{
			msg = service.users().messages().send("me", msg).execute();

			System.out.println("Message id: " + msg.getId());
			System.out.println(msg.toPrettyString());

		} catch (GoogleJsonResponseException e)
		{
			GoogleJsonError error = e.getDetails();
			if (error.getCode() == 403)
			{
				System.err.println("Unable to send message: " + e.getDetails());

			} else
			{
				throw e;
			}
			System.err.println("Unable to send message: " + e.getDetails());
		}
	}

	public static void main(String[] args) throws Exception
	{
		new NewEmailService().sendMail("A new message", """
				Dear reader,

				Hello world.

				Best regards,
				myself
				""", "sven.guderjahn@gmail.com");
	}
}
