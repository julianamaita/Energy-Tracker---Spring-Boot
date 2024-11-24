/*
 Desenvolvedores:
  Julia Ortiz   - RM 550204
  Juliana Maita - RM 99224
  Lucas Moreno  - RM 97158
*/
package br.com.fiap.et;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * Configuração de autenticação e permissões.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                // Permite acesso público ao Login, Home, e o Index, que encaminhará para a Home
                .requestMatchers("/login", "/home", "/home/**", "/").permitAll()
                .anyRequest().authenticated() // Requer autenticação para todas as outras páginas
        ).formLogin((form) -> form
                .loginPage("/login") // Define a página de login customizada
                .defaultSuccessUrl("/usuario/pedido", true) // Define para onde redirecionar após login bem-sucedido
                .permitAll()
        ).logout((logout) -> logout
                .logoutSuccessUrl("/home") // Define a página para redirecionar após logout
                .permitAll()
        );
        http.csrf((csrf) -> csrf.disable()); // Desabilita CSRF (pode ser reabilitado para produção, se necessário)
        return http.build();
    }

    /**
     * Configura a autenticação usando JDBC.
     * Insere o usuário administrador "astrogildo" se ele não existir no banco.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Fazendo uma query para checar quantos Administradores existem no banco de dados
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT COUNT(*) FROM authorities WHERE authority = 'ROLE_ADM'";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);

        if (count == null || count == 0) {
            // Se não existir nenhum Administrador, criar um e inserir no banco de dados
            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .withUser("astrogildo")
                    .password(encoder.encode("123"))
                    .roles("ADM");
        } else {
            // Apenas configurar o auth com o banco de dados
            auth.jdbcAuthentication()
                    .dataSource(dataSource);
        }
    }

    /**
     * Configuração do encoder de senha.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuração do UserDetailsManager para gerenciar usuários no banco de dados.
     */
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
        return manager;
    }
}
