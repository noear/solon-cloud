package io.grpc.solon.integration;

import org.noear.solon.core.BeanBuilder;
import org.noear.solon.core.BeanWrap;
import io.grpc.solon.annotation.GrpcService;

import java.util.Map;

/**
 * @author noear
 * @since 1.9
 */
public class GrpcServiceBeanBuilder implements BeanBuilder<GrpcService> {
    private Map<Class<?>, Object> serviceMap;

    public GrpcServiceBeanBuilder(Map<Class<?>, Object> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Override
    public void doBuild(Class<?> clz, BeanWrap bw, GrpcService anno) throws Throwable {
        serviceMap.put(clz, bw.raw());
    }
}
