package org.spring.simplybrewing.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.mail.internet.InternetAddress;
import java.util.Map;

/**
 * The type Mail.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private InternetAddress  from;
    private String to;
    // subject in the email
    private String subject;
    // data shared between service and email template
    private Map<String, String> model;
}
