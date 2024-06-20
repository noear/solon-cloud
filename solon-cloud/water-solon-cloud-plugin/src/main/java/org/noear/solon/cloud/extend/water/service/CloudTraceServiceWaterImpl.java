package org.noear.solon.cloud.extend.water.service;

import org.noear.solon.cloud.extend.water.WaterProps;
import org.noear.solon.cloud.impl.CloudTraceServiceImpl;

/**
 * 分布式跟踪服务
 *
 * @author noear
 * @since 1.2
 */
public class CloudTraceServiceWaterImpl extends CloudTraceServiceImpl {
    @Override
    public String HEADER_TRACE_ID_NAME() {
        return WaterProps.http_header_trace;
    }

    @Override
    public String HEADER_FROM_ID_NAME() {
        return WaterProps.http_header_from;
    }
}
