package com.tilmeez.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopExpressions {

    @Pointcut("execution(*  com.tilmeez.aopdemo.dao.*.*( ..))")
    public void forDaoPackage() {
    }

    // create pointcut for getter methods
    @Pointcut("execution(* com.tilmeez.aopdemo.dao.*.get*(..))")
    public void getter() {

    }

    // create pointcut for setter methods
    @Pointcut("execution(* com.tilmeez.aopdemo.dao.*.set*(..))")
    public void setter() {

    }

    // create pointcut: include package .. exclude getter/setter
    @Pointcut("forDaoPackage() &&!(getter()||setter())")
    public void forDaoPackageNoGetterSetter() {

    }
}
