package com.wuxiongwei.k8s2;

/**
 * <p>
 * Copyright: Copyright (c) 2020/6/3  11:42 上午
 * <p>
 * Company: 苏州渠成易销网络科技有限公司
 * <p>
 *
 * @author xiongwei.wu@successchannel.com
 * @version 1.0.0
 */
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;

import java.io.IOException;

public class Example {
    public static void main(String[] args) throws IOException, ApiException{
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);

        CoreV1Api api = new CoreV1Api();
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        for (V1Pod item : list.getItems()) {
            System.out.println(item.getMetadata().getName());
        }
        V1Node v1Node =new V1Node();
//        v1Node.set
        api.createNode(v1Node,null,null,null);
    }
}
