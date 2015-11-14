/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.web.registrationSys;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author snehaupadhyay
 */
@Startup
@Singleton
public class EmailUser{

    @Resource(lookup = "emailUser")
    private Session mailSession;
 
    public void sendEmail(String to) {
        MimeMessage message = new MimeMessage(mailSession);
        try {

                message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
                InternetAddress[] address = {new InternetAddress(to)};
                message.setRecipients(Message.RecipientType.TO, address);
                message.setSubject("Account Created");
                message.setSentDate(new Date());
                message.setText("Hey! Your hawk.iit.edu account has been created");
                Transport.send(message);
                } catch (MessagingException ex) {
                ex.printStackTrace();
                }
        }
}