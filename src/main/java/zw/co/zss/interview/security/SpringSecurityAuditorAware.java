package zw.co.zss.interview.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        if(SecurityUtils.getCurrentUserLogin().isPresent()){
            String username = SecurityUtils.getCurrentUserLogin().get();
            if(username.equalsIgnoreCase("anonymousUser")){
                return Optional.ofNullable("system");
            }
            return Optional.ofNullable(username);
        }else {
            return Optional.ofNullable("system");
        }
    }
}
