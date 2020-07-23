package com.wuxiongwei.k8s.fabric8;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>
 * Copyright: Copyright (c) 2020/6/2  10:37 上午
 * <p>
 * Company: 苏州渠成易销网络科技有限公司
 * <p>
 *
 * @author xiongwei.wu@successchannel.com
 * @version 1.0.0
 */
public class KubernetesClientT1 {
    public static void main(String[] args) throws Exception{
        KubernetesClient client = new DefaultKubernetesClient();

        //Config config = new ConfigBuilder().withMasterUrl("https://kubernetes.docker.internal:6443").build();
        //KubernetesClient client = new DefaultKubernetesClient(config);

//        System.out.println("List resources:");
//        NamespaceList myNs = client.namespaces().list();
//        System.out.println(myNs);
//        System.out.println();
//        ServiceList myServices = client.services().list();
//        System.out.println(myServices);
//        System.out.println();
//        ServiceList myNsServices = client.services().inNamespace("default").list();
//        System.out.println(myNsServices);
//        System.out.println();
//
//        System.out.println("Get a resource:");
//        Namespace myns = client.namespaces().withName("knative-serving").get();
//        System.out.println(myns);
//        System.out.println();
//        Service myservice = client.services().inNamespace("default").withName("kubernetes-quickstart").get();
//        System.out.println(myservice);
//        System.out.println();

//        System.out.println("Delete");
        //Namespace myns = client.namespaces().withName("myns").delete();
        //Service myservice = client.services().inNamespace("default").withName("myservice").delete();

//        System.out.println("Editing resources uses the inline builders from the Kubernetes Model");
//        Namespace mynsEdit = client.namespaces().withName("myns").edit()
//                .editMetadata()
//                .addToLabels("a", "label")
//                .endMetadata()
//                .done();
//
//        Service myserviceEdit = client.services().inNamespace("default").withName("myservice").edit()
//                .editMetadata()
//                .addToLabels("another", "label")
//                .endMetadata()
//                .done();
//
        System.out.println("In the same spirit you can inline builders to create:");
        Namespace mynsAdd = client.namespaces().createNew()
                .withNewMetadata()
                .withName("myns2")
                .addToLabels("a", "label")
                .endMetadata()
                .done();
        System.out.println(mynsAdd);
        System.out.println();

//        Service myserviceAdd = client.services().inNamespace("default").createNew()
//        Service myserviceAdd = client.services().inNamespace("myns").createNew()
//                .withNewMetadata()
//                .withName("myservice")
//                .addToLabels("another", "label")
//                .endMetadata()
//                .done();
//        System.out.println(myserviceAdd);
//        System.out.println();
//        // message=Service "myservice" is invalid: spec.ports: Required value

//        System.out.println("You can also set the apiVersion of the resource like in the case of SecurityContextConstraints :");
//        SecurityContextConstraints scc = new SecurityContextConstraintsBuilder()
//                .withApiVersion("v1")
//                .withNewMetadata().withName("scc").endMetadata()
//                .withAllowPrivilegedContainer(true)
//                .withNewRunAsUser()
//                .withType("RunAsAny")
//                .endRunAsUser()
//                .build();
//        System.out.println(scc);

//        client.events().inAnyNamespace().watch(new Watcher<Event>() {
//
//            @Override
//            public void eventReceived(Action action, Event resource) {
//                System.out.println("event " + action.name() + " " + resource.toString());
//            }
//
//            @Override
//            public void onClose(KubernetesClientException cause) {
//                System.out.println("Watcher close due to " + cause);
//            }
//
//        });


//        Pod refreshed = client.load(new FileInputStream("")).fromServer().get();
//        Boolean deleted = client.load('/workspace/pod.yml').delete();
//        LogWatch handle = client.load('/workspace/pod.yml').watchLog(System.out);

    }

    /**
     * 读取filePath的文件，将文件中的数据按照行读取到String数组中
     * @param filePath    文件的路径
     * @return         文件中一行一行的数据
     */
    public static String[] readToString(String filePath) {
        File file = new File(filePath);
        Long filelength = file.length(); // 获取文件长度
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in =new FileInputStream(file); in .read(filecontent); in .close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        String[] fileContentArr = new String(filecontent).split("\r\n");

        return fileContentArr; // 返回文件内容,默认编码
    }

}
