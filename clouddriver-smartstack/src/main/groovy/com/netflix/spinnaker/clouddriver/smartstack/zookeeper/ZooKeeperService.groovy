package com.netflix.spinnaker.clouddriver.smartstack.zookeeper

import org.apache.zookeeper.WatchedEvent
import org.apache.zookeeper.Watcher
import org.apache.zookeeper.ZooKeeper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ZooKeeperService {

  private static final Logger log = LoggerFactory.getLogger(ZooKeeperService.name)

  private String zooKeeperHost
  private String baseServiceKey
  private ZooKeeper zkClient

  ZooKeeperService(String zooKeeperHost, String baseServiceKey) {
    this.zooKeeperHost = zooKeeperHost
    this.baseServiceKey = baseServiceKey

    this.zkClient = this.connect(this.zooKeeperHost)

  }

  List<ZooKeeperApplication> loadAllApplications() {
    List<ZooKeeperApplication> apps = new ArrayList<ZooKeeperApplication>()

    try {
      String zkData = new String(this.zkClient.getData(baseServiceKey, false, null), "UTF-8")
    } catch (Exception e) {
      log.error(e.getMessage())
    }

    apps
  }

  private List<String> getApplicationAddresses(String application) {
    List<String> addresses = new ArrayList<String>()

    try {
      String zkData = new String(this.zkClient.getData(baseServiceKey, false, null), "UTF-8")
    } catch (Exception e) {
      log.error(e.getMessage())
    }

    addresses
  }

  private ZooKeeper connect(String host) throws Exception {
    zk = new ZooKeeper(host, 3000, new Watcher() {
      public void process(WatchedEvent event) {
        if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
          connSignal.countDown();
        }
      }
    });
    connSignal.await();
    return zk;
  }
}
