package com.netflix.spinnaker.clouddriver.smartstack.zookeeper

import com.netflix.spinnaker.cats.agent.Agent
import com.netflix.spinnaker.clouddriver.core.provider.agent.ExternalHealthProvider;

class ZooKeeperHealthProvider implements ExternalHealthProvider {

  public static final String PROVIDER_NAME = 'smartstack'

  final Set<String> defaultCaches = Collections.emptySet()

  final Collection<Agent> agents

  ZooKeeperHealthProvider(Collection<Agent> agents) {
    this.agents = agents
  }

  @Override
  String getProviderName() {
    return PROVIDER_NAME
  }

}
