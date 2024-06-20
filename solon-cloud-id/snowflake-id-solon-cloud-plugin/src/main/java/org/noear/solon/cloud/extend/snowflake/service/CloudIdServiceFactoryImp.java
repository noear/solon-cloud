package org.noear.solon.cloud.extend.snowflake.service;

import org.noear.solon.Utils;
import org.noear.solon.cloud.CloudProps;
import org.noear.solon.cloud.service.CloudIdService;
import org.noear.solon.cloud.service.CloudIdServiceFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author noear
 * @since 1.3
 */
public class CloudIdServiceFactoryImp implements CloudIdServiceFactory {
    long idStart;
    long workId;

    public CloudIdServiceFactoryImp(CloudProps cloudProps) {
        this.idStart = cloudProps.getIdStart();
        this.workId = Long.parseLong(cloudProps.getValue("id.workId", "0"));
    }


    private Map<String, CloudIdService> cached = new HashMap<>();

    @Override
    public CloudIdService create(String group, String service) {
        String block = group + "_" + service;
        CloudIdService tmp = cached.get(block);

        if (tmp == null) {
            Utils.locker().lock();

            try {
                tmp = cached.get(block);
                if (tmp == null) {
                    tmp = new CloudIdServiceImp(block, workId, idStart);
                    cached.put(block, tmp);
                }
            } finally {
                Utils.locker().unlock();
            }
        }

        return tmp;
    }
}
