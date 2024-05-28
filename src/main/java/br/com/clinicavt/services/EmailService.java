package br.com.clinicavt.services;

import br.com.clinicavt.models.email.EmailRecord;
import br.com.clinicavt.models.email.EmailRecordAnexo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEMail(EmailRecord email){
        try {

            var message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(email.to());
            message.setSubject(email.subject());
            message.setText(email.body());

            mailSender.send(message);

        } catch (Exception e){
            System.out.println("Erro ao enviar email " + e.getLocalizedMessage());
        }
    }




    public void sendEmailWithAnexo(EmailRecordAnexo email) throws MessagingException {
        var message = mailSender.createMimeMessage();

            var helper = new MimeMessageHelper(message, true);
            helper.setTo(email.to());
            helper.setSubject(email.subject());
            helper.setText(email.body());
            helper.addAttachment("image1.jpg", new ClassPathResource(email.file()));
            mailSender.send(message);

    }

}
