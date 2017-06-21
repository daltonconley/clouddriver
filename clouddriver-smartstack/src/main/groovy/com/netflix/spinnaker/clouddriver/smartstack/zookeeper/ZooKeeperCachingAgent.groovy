package com.netflix.spinnaker.clouddriver.smartstack.zookeeper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.netflix.spinnaker.cats.agent.AgentDataType
import com.netflix.spinnaker.cats.agent.CacheResult
import com.netflix.spinnaker.cats.agent.CachingAgent
import com.netflix.spinnaker.cats.provider.ProviderCache
import com.netflix.spinnaker.clouddriver.core.provider.agent.HealthProvidingCachingAgent


class ZooKeeperCachingAgent implements CachingAgent, HealthProvidingCachingAgent {

  private final String region
  private final ObjectMapper objectMapper
  private final String zooKeeperHost
  private final String accountName
  final String healthId = "Discovery"

  ZooKeeperCachingAgent(String zookeeperHost, String region, String accountName) {
    this.region = region
    this.zooKeeperHost = zookeeperHost
    this.accountName = accountName
    this.objectMapper = objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
  }

  @Override
  String getAgentType() {
    "${zooKeeperHost}/${ZooKeeperCachingAgent.simpleName}"
  }

  @Override
  String getProviderName() {
    'smartstack'
  }

  @Override
  Collection<AgentDataType> getProvidedDataTypes() {
    types
  }

  @Override
  CacheResult loadData(ProviderCache providerCache) {

  }

}
