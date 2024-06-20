package org.noear.solon.cloud.extend.quartz;

import org.noear.solon.Solon;
import org.noear.solon.cloud.CloudManager;
import org.noear.solon.cloud.CloudProps;
import org.noear.solon.cloud.extend.quartz.service.CloudJobServiceImpl;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;
import org.noear.solon.core.event.AppLoadEndEvent;

/**
 * @author noear
 * @since 1.11
 */
public class XPluginImp implements Plugin {
    @Override
    public void start(AppContext context) throws Throwable {
        CloudProps cloudProps = new CloudProps(context, "quartz");

        if (cloudProps.getJobEnable() == false) {
            return;
        }

        //注册Job服务
        CloudManager.register(CloudJobServiceImpl.instance);

//        CloudJobBeanBuilder.getInstance().addBuilder(Job.class, (clz, bw, anno) -> {
//            String name = Utils.annoAlias(anno.value(), anno.name());
//            CloudJobServiceImpl.instance.registerDo(name, anno.cron7x(), anno.description(), ((Job) bw.raw()).getClass());
//        });

        Solon.app().onEvent(AppLoadEndEvent.class, e -> {
            CloudJobServiceImpl.instance.start();
        });
    }
}
