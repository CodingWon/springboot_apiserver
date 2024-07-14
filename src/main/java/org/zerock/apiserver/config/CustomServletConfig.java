package org.zerock.apiserver.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.apiserver.controller.formatter.LocalDateFormatter;

@Configuration
@Log4j2
public class CustomServletConfig implements WebMvcConfigurer {

    /*
    * LocalDateFormatter 는 어노테이션이 없어서 Config로 등록 해줘야 한다.
    * */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        log.info("--------------------------");
        log.info("addFormatters");

        registry.addFormatter(new LocalDateFormatter());
    }
}