//package com.wuxiongwei.k8s2;
//
//import com.google.common.annotations.Beta;
//import io.kubernetes.client.extended.generic.GenericKubernetesApi;
//import io.kubernetes.client.extended.generic.KubernetesApiResponse;
//import io.kubernetes.client.openapi.ApiClient;
//import io.kubernetes.client.openapi.models.V1Container;
//import io.kubernetes.client.openapi.models.V1ObjectMeta;
//import io.kubernetes.client.openapi.models.V1Pod;
//import io.kubernetes.client.openapi.models.V1PodList;
//import io.kubernetes.client.openapi.models.V1PodSpec;
//import io.kubernetes.client.util.ClientBuilder;
//import java.util.Arrays;
//
///**
// * <p>
// * Copyright: Copyright (c) 2020/6/3  2:28 下午
// * <p>
// * Company: 苏州渠成易销网络科技有限公司
// * <p>
// *
// * @author xiongwei.wu@successchannel.com
// * @version 1.0.0
// */
//public class GenericClientExample {
//    public static void main(String[] args) throws Exception {
//
//        V1Pod pod =
//                new V1Pod()
//                        .metadata(new V1ObjectMeta().name("foo").namespace("default"))
//                        .spec(
//                                new V1PodSpec()
//                                        .containers(Arrays.asList(new V1Container().name("c").image("test"))));
//        ApiClient apiClient = ClientBuilder.standard().build();
//        GenericKubernetesApi<V1Pod, V1PodList> podClient =
//                new GenericKubernetesApi<>(V1Pod.class, V1PodList.class, "", "v1", "pods", apiClient);
//
//        KubernetesApiResponse<V1Pod> createResponse = podClient.create(pod);
//        if (!createResponse.isSuccess()) {
//            throw new RuntimeException(createResponse.getStatus().toString());
//        }
//        System.out.println("Created!");
//
//        KubernetesApiResponse<V1Pod> deleteResponse = podClient.delete("default", "foo");
//        if (!deleteResponse.isSuccess()) {
//            throw new RuntimeException(deleteResponse.getStatus().toString());
//        }
//        if (deleteResponse.getObject() != null) {
//            System.out.println(
//                    "Received after-deletion status of the requested object, will be deleting in background!");
//        }
//        System.out.println("Deleted!");
//    }
//}
