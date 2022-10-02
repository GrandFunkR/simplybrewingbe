package org.spring.simplybrewing.email;

import freemarker.template.Template;

import java.util.Map;

/**
 * The interface Email service.
 */
public interface EmailService {
    /**
     * Send email.
     *
     * @param request  the request
     * @param model    the model
     * @param template the template
     */
    default void sendEmail(Mail request, Map<String, String> model, Template template){}
}
