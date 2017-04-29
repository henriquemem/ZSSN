package br.com.zg;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import br.com.zg.usuario.controller.UsuarioController;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(UsuarioController.class);
    }
}