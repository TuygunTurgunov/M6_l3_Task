package uz.pdp.online.m6l2task2datarestpcmarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfigForDataRest implements RepositoryRestConfigurer {

    @Bean
    public LocalValidatorFactoryBean validator(){
        return  new LocalValidatorFactoryBean();
    }

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {

        validatingListener.addValidator("beforeCreate",validator());
        validatingListener.addValidator("beforeSave",validator());

    }

}
