package springboot.config.auth;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import springboot.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    // spring security 적용 안하는 URL 리스트
    private static final String[] AUTH_WHITELIST = {
            "/",
            "/css/**",
            "/image/**",
            "/js/**",
            "/h2-console/**"
    };
    // 인증 필요 리스트
    private static final String[] VERIFICATION_AUTH_LIST = {
            "/api/v1/**"
    };

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests(
                        request->request
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                .requestMatchers(VERIFICATION_AUTH_LIST).hasRole(Role.USER.name())
                                .anyRequest().authenticated()
                )
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return http.build();
    }
}
