package org.noear.solon.cloud.extend.xxljob.service;

import com.xxl.job.core.executor.XxlJobExecutor;
import org.noear.solon.cloud.CloudJobHandler;
import org.noear.solon.cloud.model.JobHolder;
import org.noear.solon.cloud.service.CloudJobService;
import org.noear.solon.logging.utils.TagsMDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author noear
 * @since 1.4
 */
public class CloudJobServiceImpl implements CloudJobService {
    static final Logger log = LoggerFactory.getLogger(CloudJobServiceImpl.class);

    @Override
    public boolean register(String name, String cron7x, String description, CloudJobHandler handler) {
        JobHolder jobHolder = new JobHolder(name, cron7x, description, handler);

        XxlJobExecutor.registJobHandler(name, new IJobHandlerImpl(jobHolder));

        TagsMDC.tag0("CloudJob");
        log.info("CloudJob: Handler registered name:" + name + ", class:" + handler.getClass().getName());
        TagsMDC.tag0("");
        return true;
    }

    @Override
    public boolean isRegistered(String name) {
        return XxlJobExecutor.loadJobHandler(name) != null;
    }
}
