package com.netflix.spinnaker.clouddriver.smartstack

import com.netflix.spinnaker.clouddriver.smartstack.config.SmartstackConfigurationProperties
import com.netflix.spinnaker.clouddriver.smartstack.zookeeper.ZooKeeperCachingAgent
import com.netflix.spinnaker.clouddriver.smartstack.zookeeper.ZooKeeperHealthProvider
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Scope

import java.util.regex.Pattern

@EnableConfigurationProperties(SmartstackConfigurationProperties)
@ConditionalOnProperty('smartstack.provider.enabled')
@ComponentScan(["com.netflix.spinnaker.clouddriver.smartstack"])
class SmartstackProvider {

  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Bean
  @ConfigurationProperties("smartstack.provider")
  SmartstackConfigurationProperties smartstackConfigurationProperties() {
    new SmartstackConfigurationProperties()
  }

  @Bean
  ZooKeeperHealthProvider zooKeeperHealthProvider(SmartstackConfigurationProperties smartstackConfigurationProperties) {
    List<ZooKeeperHealthProvider> agents = []
    smartstackConfigurationProperties.zookeeperNodes.each { SmartstackConfigurationProperties.ZookeeperNode zookeeperConfig ->
      zookeeperConfig.regions.each { region ->
        String zookeeperHost = zookeeperConfig.host.replaceAll(Pattern.quote('{{region}}'), region)
        agents << new ZooKeeperCachingAgent(zookeeperConfig.host, region, zookeeperConfig.name)
      }
    }
    ZooKeeperHealthProvider zookeeperHealthProvider = new ZooKeeperHealthProvider(agents)
    zookeeperHealthProvider
  }
}
