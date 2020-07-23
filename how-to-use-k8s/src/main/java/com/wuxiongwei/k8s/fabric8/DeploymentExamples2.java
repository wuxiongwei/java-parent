
package com.wuxiongwei.k8s.fabric8;

import io.fabric8.kubernetes.api.model.ServiceAccount;
import io.fabric8.kubernetes.api.model.ServiceAccountBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DeploymentExamples2 {
  private static final Logger logger = LoggerFactory.getLogger(DeploymentExamples2.class);

  public static void main(String[] args) throws InterruptedException {
//    Config config = new ConfigBuilder().build();
//    KubernetesClient client = new DefaultKubernetesClient(config);
    KubernetesClient client = new DefaultKubernetesClient();

    try {


//      System.out.println("In the same spirit you can inline builders to create:");
//      Namespace mynsAdd = client.namespaces().createNew()
//              .withNewMetadata()
//              .withName("myns3")
//              .addToLabels("a", "label")
//              .endMetadata()
//              .done();
//      System.out.println(mynsAdd);
//      System.out.println();

      // Create a namespace for all our stuff
//      Namespace ns = new NamespaceBuilder().withNewMetadata().withName("myns4").addToLabels("a", "label").endMetadata().build();
//      Namespace ns = new NamespaceBuilder().withNewMetadata().withName("thisisatest").addToLabels("this", "rocks").endMetadata().build();
//      log("Created namespace", client.namespaces().createOrReplace(ns));

      ServiceAccount fabric8 = new ServiceAccountBuilder().withNewMetadata().withName("fabric8").endMetadata().build();
//
      client.serviceAccounts().inNamespace("myns4").createOrReplace(fabric8);
//      for (int i = 0; i < 2; i++) {
//        System.err.println("Iteration:" + (i+1));
//        Deployment deployment = new DeploymentBuilder()
//                .withNewMetadata()
//                .withName("nginx")
//                .endMetadata()
//                .withNewSpec()
//                .withReplicas(1)
//                .withNewTemplate()
//                .withNewMetadata()
//                .addToLabels("app", "nginx")
//                .endMetadata()
//                .withNewSpec()
//                .addNewContainer()
//                .withName("nginx")
//                .withImage("nginx")
//                .addNewPort()
//                .withContainerPort(80)
//                .endPort()
//                .endContainer()
//                .endSpec()
//                .endTemplate()
//                .withNewSelector()
//                .addToMatchLabels("app", "nginx")
//                .endSelector()
//                .endSpec()
//                .build();
//
//
//        deployment = client.apps().deployments().inNamespace("thisisatest").create(deployment);
//        log("Created deployment", deployment);
//
//        System.err.println("Scaling up:" + deployment.getMetadata().getName());
//        client.apps().deployments().inNamespace("thisisatest").withName("nginx").scale(2, true);
//        log("Created replica sets:", client.apps().replicaSets().inNamespace("thisisatest").list().getItems());
//        System.err.println("Deleting:" + deployment.getMetadata().getName());
//        client.resource(deployment).delete();
//      }
//      log("Done.");

    }finally {
      client.namespaces().withName("thisisatest").delete();
      client.close();
    }
  }


  private static void log(String action, Object obj) {
    logger.info("{}: {}", action, obj);
    System.out.println(action+" "+obj);
  }

  private static void log(String action) {
    logger.info(action);
  }
}
