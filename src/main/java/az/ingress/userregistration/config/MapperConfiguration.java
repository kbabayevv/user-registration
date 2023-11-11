package az.ingress.userregistration.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MapperConfiguration {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
