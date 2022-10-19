package cr.ac.una.aop;

import cr.ac.una.entity.Log;
import cr.ac.una.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Aspect
@Component
@Slf4j
public class MocionAop {

    private final LogRepository LR;
    Date date = new Date(System.currentTimeMillis());

    @Before("execution(* cr.ac.una.repository.MocionRepository.findAll(..))")
    public void logBefore(JoinPoint joinpoint) {
        log.info("Verificacion: {}", joinpoint.getSignature().getName());
        LR.save(new Log(null, joinpoint.getSignature().getName(), "Mocion", date));
    }

    @Before("execution(* cr.ac.una.repository.MocionRepository.save(..))")
    public void logBefore2(JoinPoint joinpoint) {
        log.info("Verificacion: {}", joinpoint.getSignature().getName());
        LR.save(new Log(null, joinpoint.getSignature().getName(), "Mocion", date));
    }

    @Before("execution(* cr.ac.una.repository.MocionRepository.findById(..))")
    public void logBefore3(JoinPoint joinpoint) {
        log.info("Verificacion: {}", joinpoint.getSignature().getName());
        LR.save(new Log(null, joinpoint.getSignature().getName(), "Mocion", date));
    }

    @Before("execution(* cr.ac.una.repository.MocionRepository.delete(..))")
    public void logBefore4(JoinPoint joinpoint) {
        log.info("Verificacion: {}", joinpoint.getSignature().getName());
        LR.save(new Log(null, joinpoint.getSignature().getName(), "Mocion", date));
    }
}
