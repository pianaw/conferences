package org.waveaccess.conferences.config;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Bean
    TomcatConnectorCustomizer headerRejectionCustomizer() {
        return (connector) ->
                ((AbstractHttp11Protocol<?>)connector.getProtocolHandler()).setRejectIllegalHeader(false);
    }

}

