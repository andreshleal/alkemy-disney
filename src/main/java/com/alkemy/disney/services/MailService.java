package com.alkemy.disney.services;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    public String sendEmail(String email) throws IOException {


        Email from = new Email("andres.h.leal@hotmail.com");
        String subject = "nuevo registro alkemy disney";
        Email to = new Email(email);
        Content content = new Content("text/plain", "registro exitoso");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
//	      System.out.println(response.getStatusCode());
//	      System.out.println(response.getBody());
//	      System.out.println(response.getHeaders());
            logger.info(response.getBody());
            return response.getBody();
        } catch (IOException ex) {
            throw ex;
        }

    }


}