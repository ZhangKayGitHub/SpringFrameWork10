package com.kay.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
public class Logger {

    /**
     * 前置通知
     */
    public void beforePrintlog(){
        System.out.println("Logger类中的beforePrintlog方法开始记录日志了.....");
    }
    /**
     * 后置通知
     */
    public void afterReturnPrintlog(){
        System.out.println("后置通知Logger类中的afterReturnPrintlog方法开始记录日志了.....");
    }
    /**
     * 异常通知
     */
    public void afterThrowingPrintlog(){
        System.out.println("异常通知Logger类中的afterThrowingPrintlog方法开始记录日志了.....");
    }
    /**
     * 最终通知
     */
    public void afterPrintlog(){
        System.out.println("最终通知Logger类中的afterPrintlog方法开始记录日志了.....");
    }

    /**
     * 环绕通知
     * 问题：
     *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了
     * 分析：
     *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
     * 解决:
     *      Spring框架为我们提供了一个接口，ProceedingJoinPoint，该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *      该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
     *
     * spring 中的环绕通知：
     *      它是spring框架为我们提供的一种在代码中手动控制增强方法何时执行的方法
     */
    public Object aroundPrintlog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try{
            Object[] args = pjp.getArgs();//得到方法执行所需的参数
            System.out.println("Logger类中的aroundPrintlog方法开始记录日志了....");
            rtValue = pjp.proceed(args);//明确调用业务曾方法（切入点方法）
            System.out.println("Logger类中的aroundPrintlog方法开始记录日志了....");
            return rtValue;
        }catch (Throwable t){
            System.out.println("Logger类中的aroundPrintlog方法开始记录日志了....");
            throw new RuntimeException(t);
        }finally {
            System.out.println("Logger类中的aroundPrintlog方法开始记录日志了....");
        }


    }
}
