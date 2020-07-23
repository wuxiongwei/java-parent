package com.wuxiongwei.k8s2;

/**
 * <p>
 * Copyright: Copyright (c) 2020/6/3  2:23 下午
 * <p>
 * Company: 苏州渠成易销网络科技有限公司
 * <p>
 *
 * @author xiongwei.wu@successchannel.com
 * @version 1.0.0
 */
import com.google.gson.reflect.TypeToken;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.Watch;

import java.io.IOException;

public class WatchExample {
    public static void main(String[] args) throws IOException, ApiException{
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);

        CoreV1Api api = new CoreV1Api();

        Watch<V1Namespace> watch = Watch.createWatch(
                client,
                api.listNamespaceCall(null, null, null, null, null, 5, null, null, Boolean.TRUE, null),
                new TypeToken<Watch.Response<V1Namespace>>(){}.getType());

        for (Watch.Response<V1Namespace> item : watch) {
            System.out.printf("%s : %s%n", item.type, item.object.getMetadata().getName());
        }
    }
}
