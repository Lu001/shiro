package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	@Around("execution(* service.ProductService.*(..))")
	public Object log(ProceedingJoinPoint joinPoint)throws Throwable{
		System.out.println("start"+joinPoint.getSignature().getName());
		Object object = joinPoint.proceed();
		System.out.println("end"+joinPoint.getSignature().getName());
		return object;
	}
}
