package lp.apresentacao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir todas as rotas
                .allowedOrigins("*") // Permitir todas as origens (ajuste se necessário)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permitir todos os cabeçalhos
                .allowCredentials(true); // Permitir envio de credenciais
    }
}