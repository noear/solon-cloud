package org.noear.solon.cloud.extend.opentracing;

import org.noear.solon.Utils;
import org.noear.solon.cloud.CloudProps;
import org.noear.solon.cloud.tracing.TracingManager;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear
 * @since 1.4
 */
public class XPluginImp implements Plugin {

    @Override
    public void start(AppContext context) {
        CloudProps cloudProps = new CloudProps(context,"opentracing");

        if (cloudProps.getTraceEnable() == false) {
            return;
        }

        if (Utils.isEmpty(cloudProps.getServer())) {
            return;
        }

        TracingManager.enable(cloudProps.getTraceExclude());
    }
}
