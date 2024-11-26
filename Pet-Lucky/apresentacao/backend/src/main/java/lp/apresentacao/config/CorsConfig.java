package lp.apresentacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/") // Define que todas as URLs da aplicação terão CORS habilitado
                .allowedOrigins("") // Permite qualquer origem (você pode especificar domínios específicos em vez de "")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite os métodos HTTP desejados
                .allowedHeaders("*") // Permite qualquer cabeçalho (pode ser restringido)
                .allowCredentials(true); // Permite envio de credenciais (cookies, autorizações, etc.)
}
}