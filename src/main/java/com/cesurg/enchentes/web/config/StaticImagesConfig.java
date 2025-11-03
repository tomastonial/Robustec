package com.cesurg.enchentes.web.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class StaticImagesConfig implements WebMvcConfigurer {

    private static final String CAMINHO = "C:/Users/tonia/Desktop/ADS4SEMESTRE/TrabalhoFinalEnchente/imagens/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String baseUri = Paths.get(CAMINHO).toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/imagens/**")
                .addResourceLocations(baseUri);
    }
}
