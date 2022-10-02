/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Personal / Commercial License.
 * See LICENSE_PERSONAL / LICENSE_COMMERCIAL in the project root for license information on type of purchased license.
 */

package org.spring.simplybrewing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
@Slf4j
public class SimplyBrewingApplication {
    @Value("${application_name}")
    private String applicationName;

    @Value("${application_version}")
    private String applicationVersion;

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UCT"));

        SpringApplication.run(SimplyBrewingApplication.class, args);
    }

    @PostConstruct
    public void init() {
        log.info("\n**********************************************\n" +
                "* Application name:\t\t" + applicationName + "\n" +
                "* Application version:\t" + applicationVersion + "\n" +
                "* Port:\t\t\t\t\t" +  port + "\n" +
                "**********************************************\n"
        );

    }
}
