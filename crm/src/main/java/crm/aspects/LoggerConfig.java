package crm.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerConfig {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(String getChanged*(..))")
	public void logEveryGet(JoinPoint jp) {
		log.info("Before advice implementation - " 
	        + jp.getTarget().getClass() + " - Executing Before " 
	        + jp.getSignature().getName() + "() method");
	}
	@Before("execution(@org.springframework.web.bind.annotation.GetMapping * *.*(..))")
	public void logAllGetMapping(JoinPoint jp) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username;
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		log.info(username + " accessing - " + jp.getTarget().getClass() + " ; Executing " + jp.getSignature().getName() + "() method");
	}

}
