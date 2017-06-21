package com.netflix.spinnaker.clouddriver.smartstack.zookeeper

class ZooKeeperApplication {

  private String name
  private List<String> addresses

  ZooKeeperApplication(String name, List<String> addresses) {
    this.name = name
    this.addresses = addresses
  }
}
