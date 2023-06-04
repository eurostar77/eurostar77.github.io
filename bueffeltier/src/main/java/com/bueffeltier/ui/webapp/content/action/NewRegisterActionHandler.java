//package com.bueffeltier.ui.webapp.content.action;
//
//import com.bueffeltier.ui.webapp.content.viewhandler.HeaderViewHandler;
//
//private static HeaderViewHandler instance;
//
//private HeaderViewHandler()
//{
//	super();
//}
//
//public static HeaderViewHandler getInstance()
//{
//	if (instance == null)
//	{
//		instance = new HeaderViewHandler();
//	}
//	return instance;
//}
/////*
//// * To change this license header, choose License Headers in Project Properties.
//// * To change this template file, choose Tools | Templates
//// * and open the template in the editor.
//// */
////package com.mycompany.bueffeltier.servlet.content;
////
////import com.mycompany.bueffeltier.servlet.RequestController;
////import com.mycompany.bueffeltier.servlet.SendMail;
////import j2html.tags.Tag;
////
/////**
//// *
//// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
//// */
////public class NewRegisterHandler extends AbstractContentTypeHandler{
////
////    final String type = "PAGE_STRUCTURE";
////
////    private boolean enableArtikelAnzeigenButton;
////    private boolean enableAblageLeerenButton;
////    private boolean enableNeueSeiteButton;
////
//////    definiere pageActions? oder Link-Actions?
////
////    public NewRegisterHandler(
////            RequestController requestController, int contentId) {
////        super(requestController, contentId);
////    }
////
////    @Override
////    public void doPostAction(){
////
////        String registerUsername = requestController.request.getParameter("name");
////        String registerPassword = requestController.request.getParameter("password");
////        String registerEmail = requestController.request.getParameter("e-mail");
////
////        // todo: hier ist die Session solange falsch, bis confirmation-mail gesendet wurde!!
////        // todo: prüfe doppelte key-mail-adresse und gleiche namen ab.
////        // todo: link aus Settings beziehen, oder aus PageTree.
////        String activationKey = "testkey12345";
////        String aktivationLink = "http://localhost:8084/bueffeltier/account-activate" + activationKey;
////
////        SendMail.send(
////                registerEmail,
////                "Willkommen bei bueffeltier!", // todo: stammdaten und/oder einstellungen für texte etc...
////                "Hallo " + registerUsername +",\n\n herzlich willkommen bei bueffeltier! der Online-Universität!\nUnter folgendem Link kannst du dein Benutzerkonto aktivieren: \n" + aktivationLink, // todo: s.o.
////                "sven.guderjahn@gmail.com", // todo: s.o.
////                "JTZQaafXI32YV2d7aZ"); // todo: s.o.
////        // todo: user vorerst anlegen. nach gewisser zeit wieder löschen, oder wenn session beendet ist. oder gar nicht löschen?
////
////        // todo: user in db anlegen, wenn nicht vorhanden.
////        //       user-name und e-mail dürfen in user-db nicht doppelt sein.
////        //       füge user init-method hinzu
////
////
////        // todo: OnRegistrationCompleteEvent that our controller is sending out
////        private String appUrl;
////	    private Locale locale;
////	    private User user;
////
////
////        /** Here, the confirmRegistration method will receive the
////         * OnRegistrationCompleteEvent, extract all the necessary User
////         * information from it, create the verification token, persist it,
////         * and then send it as a parameter in the “Confirm Registration” link.
////         **/
////
////As was mentioned above, any javax.mail.AuthenticationFailedException thrown by JavaMailSender will be handled by the controller.
////            OnRegistrationCompleteEvent(
////	      User user, Locale locale, String appUrl)
////
////                    private void confirmRegistration(OnRegistrationCompleteEvent event) {
////	        User user = event.getUser();
////	        String token = UUID.randomUUID().toString();
////	        service.createVerificationToken(user, token);
////
////	        String recipientAddress = user.getEmail();
////	        String subject = "Registration Confirmation";
////	        String confirmationUrl
////	          = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
////	        String message = messages.getMessage("message.regSucc", null, event.getLocale());
////
////	        SimpleMailMessage email = new SimpleMailMessage();
////	        email.setTo(recipientAddress);
////	        email.setSubject(subject);
////	        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
////	        mailSender.send(email);
////	    }
////
////        // When the user receives the “Confirm Registration” link
////                    //they should click on it.
////        // Once they do – the controller will extract the value of the token
////        // parameter in the resulting GET request and will use it to enable the User.
////
////
////                    Action: Registration COnfirm:
////                    @GetMapping("/regitrationConfirm")
////	public String confirmRegistration
////	  (WebRequest request, Model model, @RequestParam("token") String token) {
////
////	    Locale locale = request.getLocale();
////
////	    VerificationToken verificationToken = service.getVerificationToken(token);
////	    if (verificationToken == null) {
////	        String message = messages.getMessage("auth.message.invalidToken", null, locale);
////	        model.addAttribute("message", message);
////	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
////	    }
////
////	    User user = verificationToken.getUser();
////	    Calendar cal = Calendar.getInstance();
////	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
////	        String messageValue = messages.getMessage("auth.message.expired", null, locale)
////	        model.addAttribute("message", messageValue);
////	        return "redirect:/badUser.html?lang=" + locale.getLanguage();
////	    }
////
////	    user.setEnabled(true);
////	    service.saveRegisteredUser(user);
////	    return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
////	}
////
////    @Override
////    public void doAjaxAction() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
////
////    @Override
////    public Tag writeHtml() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
////
////    @Override
////    public String writeCss() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
////    }
////
////    @Override
////    public void doAjaxAction() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
////
////    @Override
////    public Tag writeHtml() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
////
////    @Override
////    public String writeCss() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
////
////}
