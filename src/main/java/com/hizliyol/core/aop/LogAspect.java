package com.hizliyol.core.aop;

import ch.qos.logback.classic.Logger;
import com.hizliyol.core.exception.ControllerException;
import com.hizliyol.core.exception.DaoException;
import com.hizliyol.core.exception.ServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * Created by bilge_gilleez on 18.01.2018.
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.hizliyol.core.controller.*.set*(..)) || execution(public * com.hizliyol.core.service.*.set*(..))" +
      "  || execution(public * com.hizliyol.core.dao.*.set*(..)) || execution(public * com.hizliyol.core.data.*.set*(..))")
    public void setter(){

    }

    private enum Type{
        CONTROLLER,SERVICE,DAO
    }

    @Pointcut("execution(public * com.hizliyol.core.controller.*.get*(..)) || execution(public * com.hizliyol.core.service.*.get*(..))" +
      "  || execution(public * com.hizliyol.core.dao.*.get*(..)) || execution(public * com.hizliyol.core.data.*.get*(..))")
    public void getter(){

    }

    @Around("execution(public * com.hizliyol.core.controller.*.*(..)) && !setter() && !getter()")
    public Object pointcutController(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        obj = getResultAspect(joinPoint,Type.CONTROLLER);
        return obj;
    }

    @Around("execution(public * com.hizliyol.core.service.*.*(..))")
    public Object pointcutService(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        obj = getResultAspect(joinPoint,Type.SERVICE);
        return obj;
    }

    @Around("execution(public * com.hizliyol.core.dao.*.*(..)) ")
    public Object pointcutDao(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        obj = getResultAspect(joinPoint,Type.DAO);
        return obj;
    }

    @Around("execution(public * com.hizliyol.core.data.*.*(..))")
    public Object pointcutDataDao(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        obj = getResultAspect(joinPoint,Type.DAO);
        return obj;
    }

    private Object getResultAspect(ProceedingJoinPoint joinPoint,Type type) {
        Object obj = null;
        String packageName = joinPoint.getSignature().getDeclaringTypeName();
        Logger logger = (Logger) LoggerFactory.getLogger(packageName);
        StringBuffer builder = new StringBuffer("");
        for (Object arg :joinPoint.getArgs()) {
            builder.append(arg).append(",");
        }


        long start = System.currentTimeMillis();
        try {
            obj = joinPoint.proceed();
            long elapsedtime = System.currentTimeMillis() - start;
            logger.info(new StringBuffer( joinPoint.getSignature().getName()).append("(").append(builder.toString()).append(") Time : ").append(elapsedtime).append(" ms").toString());
            synchronized (this){
                MDC.clear();
            }
        } catch (Throwable throwable) {
            long elapsedtime = System.currentTimeMillis() - start;
            logger.error(new StringBuffer( joinPoint.getSignature().getName()).append("(").append(builder.toString()).append(") Time : ").append(elapsedtime).append(" ms").toString());
            logger.error(getStackTrace(throwable.getStackTrace()));
            synchronized (this){
                MDC.clear();
            }
            switch (type){
                case CONTROLLER:
                    throw new ControllerException(throwable);
                case DAO:
                    throw new DaoException(throwable);
                case SERVICE:
                    throw new ServiceException(throwable);
            }
        }
        return obj;
    }

    private String getStackTrace(StackTraceElement[] arr){
        StringBuffer builder = new StringBuffer();
        for (StackTraceElement element :arr) {
            builder.append(element.toString()).append("\n");
        }
        return builder.toString();
    }
}
