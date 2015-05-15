package by.itechart.flowerty.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Rostislav on 15-May-15
 */

@Component
public class PasswordEncoder extends Md5PasswordEncoder {
    private final String SALT = "pHvDStTEwjjneMCvxuqUKVyycXZRfXMw";

    @Bean
    public Md5PasswordEncoder passwordEncoder() throws Exception {
        return new Md5PasswordEncoder();
    }

    public String getSalt() {
        return SALT;
    }
}
