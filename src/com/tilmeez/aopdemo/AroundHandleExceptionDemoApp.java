package com.tilmeez.aopdemo;

import com.tilmeez.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundHandleExceptionDemoApp {

    private static Logger myLogger =
            Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        MyLoggerConfig loggerConfig = context.getBean("myLoggerConfig", MyLoggerConfig.class);

        Logger loggerParent = Logger.getLogger(AnnotationConfigApplicationContext.class.getName());

        // get the bean from spring container
        TrafficFortuneService theFortuneService =
                context.getBean("trafficFortuneService", TrafficFortuneService.class);

        myLogger.info("\nMain program: AroundDemoApp");

        myLogger.info("Calling getFortune");

        boolean tripWire = true;
        String data = theFortuneService.getFortune(tripWire);

        myLogger.info("\nMy fortune is:" + data);

        myLogger.info("Finished");

        //close the context
        context.close();
    }
}







