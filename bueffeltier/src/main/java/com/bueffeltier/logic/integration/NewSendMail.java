package com.bueffeltier.logic.integration;
//package com.mycompany.bubbles.servlet;
//
//import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//
///**
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class NewSendMail {
//
//    public static LogFrame log = LogFrame.getInstance();
//
//    /**
//     * @param to = email adress
//     * @param subj = subject
//     * @param msg = message
//     * @param user = email account user
//     * @param pass = email account password
//     */
//    public static void send(
//            String to,
//            String subj,
//            String msg,
//            final String user,
//            final String pass){
//
//        //create an instance of Properties Class
//        Properties properties = new Properties();
//
//        /*  Specifies the IP address of your default mail server
//     	      for e.g if you are using gmail server as an email sever
//            you will pass smtp.gmail.com as value of mail.smtp host.
//            As shown here in the code.
//            Change accordingly, if your email id is not a gmail id
//        */
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        // below mentioned mail.smtp.port is optional
//        properties.put("mail.smtp.port", "587");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//
//        properties.put("mail.imap.ssl.enable", "true"); // required for Gmail
//        properties.put("mail.imap.auth.mechanisms", "XOAUTH2");
//        Session session = Session.getInstance(properties);
//        Store store = session.getStore("imap");
//        store.connect("imap.gmail.com", username, oauth2_access_token);
//
//        /* Pass Properties object(props) and Authenticator object
//           for authentication to Session instance
//        */
//
//        Session session = Session.getInstance(properties,new javax.mail.Authenticator()
//        {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication()
//            {
//  	 	         return new PasswordAuthentication(user,pass);
//            }
//        });
//
//        try {
//
//     	      /*  Create an instance of MimeMessage,
//     	          it accept MIME types and headers
//     	      */
//
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(user));
//            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//            message.setSubject(subj);
//            message.setText(msg);
//
//            /* Transport class is used to deliver the message to the recipients */
//
//            Transport.send(message);
//        }
//        catch(Exception e) {
//    	     e.printStackTrace();
//        }
//    }
//}
//
