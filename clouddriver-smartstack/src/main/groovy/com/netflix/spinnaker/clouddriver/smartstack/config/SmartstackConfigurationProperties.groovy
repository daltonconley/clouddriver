package com.netflix.spinnaker.clouddriver.smartstack.config

import groovy.transform.ToString

@ToString(includeNames = true)
class SmartstackConfigurationProperties {

  @ToString(includeNames = true)
  static class ZookeeperNode {
    String name
    List<String> regions
    String host;
  }

  List<ZookeeperNode> zookeeperNodes;
}
