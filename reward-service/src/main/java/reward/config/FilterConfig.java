package reward.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public FilterRegistrationBean jwtFilterBean() {
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(jwtFilter);
        // provide endpoints which needs to be restricted.
        // All Endpoints would be restricted if unspecified
        return filter;
    }
}
