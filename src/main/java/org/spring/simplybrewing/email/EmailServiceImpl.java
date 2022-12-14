package org.spring.simplybrewing.email;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 * The type Email service.
 */
@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    /**
     * Instantiates a new Email service.
     *
     * @param mailSender the mail sender
     */
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Mail request, Map<String, String> model, Template template) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(request.getTo());
            helper.setFrom(request.getFrom());
            helper.setSubject(request.getSubject());
            helper.setText(html, true);
            mailSender.send(message);}

        catch (MessagingException | TemplateException | IOException e) {
            e.printStackTrace();
        }
    }
}
