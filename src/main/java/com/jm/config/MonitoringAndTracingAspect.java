package com.jm.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MonitoringAndTracingAspect {

    private final Tracer tracer;
    private final MeterRegistry meterRegistry;

    public MonitoringAndTracingAspect(Tracer tracer, MeterRegistry meterRegistry) {
        this.tracer = tracer;
        this.meterRegistry = meterRegistry;
    }

    @Around("execution(* your.package.YourApiService.*(..))")
    public Object monitorAndTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Span span = tracer.spanBuilder(methodName).startSpan();
        try {
            Object result = joinPoint.proceed();
            // Add any specific metric increment or tracing logic here
            meterRegistry.counter(methodName + "_requests").increment();
            return result;
        } finally {
            span.end();
        }
    }
}

