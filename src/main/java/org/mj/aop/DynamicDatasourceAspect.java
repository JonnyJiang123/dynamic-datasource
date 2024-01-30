package org.mj.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.mj.annotation.DynamicDatasource;
import org.mj.manager.DynamicDatasourceManager;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Created with IntelliJ IDEA.
 * author: jun.jiang
 * Date: 2024/1/30
 * Time: 13:56
 * Description:
 */
@Aspect
@Order(1)
@Component
public class DynamicDatasourceAspect {
    @Pointcut("@within(org.mj.annotation.DynamicDatasource)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        Class<?> targetClass = target.getClass();
        String currentDatasource = null;
        if (targetClass.isAnnotationPresent(DynamicDatasource.class)) {
            DynamicDatasource dynamicDatasource = targetClass.getAnnotation(DynamicDatasource.class);
            currentDatasource = dynamicDatasource.value();
            DynamicDatasourceManager.setCurrentDatasource(currentDatasource);
        }
        try{
            Object result = null;
            result = joinPoint.proceed();
            return result;
        }catch (Throwable e){
            throw new Throwable(e);
        }finally {
            DynamicDatasourceManager.removeCurrentDatasource();
        }
    }
}
