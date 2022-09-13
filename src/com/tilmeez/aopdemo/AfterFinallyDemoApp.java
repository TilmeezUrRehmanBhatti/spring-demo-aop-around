package com.tilmeez.aopdemo;

import com.tilmeez.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterFinallyDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        // call method to find the accounts
        List<Account> theAccounts = null;

        try {
            // add a boolean flag to simulate exceptions
            boolean tripWire = false;
            theAccounts = theAccountDAO.findAccounts(tripWire);

        } catch (Exception e) {
            System.out.println("\n\nMain Program ... caught exception: " + e);
        }

        // display the accounts
        System.out.println("\n\nMain program: AfterThrowingDemoApp");
        System.out.println("---");

        System.out.println(theAccounts);

        System.out.println("\n");

        //close the context
        context.close();
    }
}







