package karan.societe.hiring.apparel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

import karan.societe.hiring.apparel.service.DiscountSelector;
import karan.societe.hiring.apparel.service.MaxDiscountSelector;

@Configuration
@ComponentScan
public class ApplicationConfig {

    @Bean
    public DefaultConversionService conversionService() {
        return new DefaultConversionService();
    }

    @Bean
    public DiscountSelector maxDiscountSelector() {
        // this bean must be based on some configuration property
        return new MaxDiscountSelector();
    }
}
