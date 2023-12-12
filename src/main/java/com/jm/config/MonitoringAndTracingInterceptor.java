package com.jm.config;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.inject.Inject;

@Interceptor
@Traced // Custom interceptor binding annotation
@Priority(Interceptor.Priority.APPLICATION)
public class MonitoringAndTracingInterceptor {

    @Inject
    private Tracer tracer;

    @AroundInvoke
    public Object monitorAndTrace(InvocationContext context) throws Exception {
        String methodName = context.getMethod().getName();
        Span span = tracer.spanBuilder(methodName).startSpan();
        try {
            return context.proceed();
        } finally {
            span.end();
        }
    }
}

