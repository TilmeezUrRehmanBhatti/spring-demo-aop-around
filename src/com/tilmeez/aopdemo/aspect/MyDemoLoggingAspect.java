package com.tilmeez.aopdemo.aspect;

import com.tilmeez.aopdemo.Account;
import com.tilmeez.aopdemo.AroundHandleExceptionDemoApp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    private static Logger myLogger =
            Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

    @Around("execution(* com.tilmeez.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // new, let's execute the method
        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Throwable e) {
            // log the exception
            myLogger.warning(e.getMessage());

            // give user a custom message
            result = "Major Accident!!! but don't worries , it will be clear in 5 minutes";
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=======>>>>> Duration: " + duration / 1000.0 + " secounds");

        return result;

    }

    @After("execution(* com.tilmeez.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJointPoint) {

        // print out which method we are advising on
        String method = theJointPoint.toShortString();
        System.out.println("\n=====>>> Executing @After(finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.tilmeez.aopdemo.dao.AccountDAO.findAccounts(..  ))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJointPoint, Throwable theExc
    ) {
        // print out which method we are advising on
        String method = theJointPoint.toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=====> The exception is : " + theExc);

    }

    @AfterReturning(
            pointcut = "execution(* com.tilmeez.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(
            JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method:  " + method);

        // print out the result of the method call
        System.out.println("\n=====>>> result is : " + result);

        // post-process the data ... modify it

        // convert the account names to Uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>>> result is : " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts
        for (Account tempAccount : result) {

            // get uppercase version of names
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);

        }

    }

    @Before("com.tilmeez.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJointPoint) {
        System.out.println("\n====>>> Executing @Before advice on addAccount");

        // display the method signature
        MethodSignature methodSig = (MethodSignature) theJointPoint.getSignature();

        System.out.println("Method: " + methodSig);

        // display method arguments

        // get args
        Object[] args = theJointPoint.getArgs();

        // loop thru args
        for (Object tempArgs : args) {
            System.out.println(tempArgs);

            if (tempArgs instanceof Account) {

                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArgs;

                System.out.println("account name : " + theAccount.getName());
                System.out.println("account level : " + theAccount.getLevel());

            }
        }
    }

}
