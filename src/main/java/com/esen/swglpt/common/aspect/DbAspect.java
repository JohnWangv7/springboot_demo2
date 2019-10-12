package com.esen.swglpt.common.aspect;

import com.power.datasource.aspect.DataSourceAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wangdong
 * */
@Aspect
@Component
public class DbAspect extends DataSourceAspect {

    @Pointcut("execution(* com.esensoft.swglpt.dao.*.*(..))")
    @Override
    protected void datasourceAspect() {
        super.datasourceAspect();
    }
}
