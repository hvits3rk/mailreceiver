package com.github.hvits3rk.mailreceiver.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.github.hvits3rk.mailreceiver.repository")
@EntityScan("com.github.hvits3rk.mailreceiver.entity")
public class ApplicationConfig {
}
