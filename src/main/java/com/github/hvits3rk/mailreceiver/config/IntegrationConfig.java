package com.github.hvits3rk.mailreceiver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.mail.ImapIdleChannelAdapter;
import org.springframework.integration.mail.ImapMailReceiver;

import java.util.Properties;

@EnableIntegration
@Configuration
public class IntegrationConfig {

    @Value("${com.github.hvits3rk.mail.url}")
    private String mailUrl;

    @Value("${com.github.hvits3rk.mail.folder}")
    private String mailFolder;

    private Properties javaMailProperties() {
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.setProperty("mail.imap.socketFactory.fallback", "false");
        javaMailProperties.setProperty("mail.store.protocol", "imaps");
        javaMailProperties.setProperty("mail.imaps.partialfetch", "false");
        javaMailProperties.setProperty("mail.imaps.timeout", "300000");
        javaMailProperties.setProperty("mail.mime.charset", "utf-8");
        javaMailProperties.setProperty("mail.debug", "false");

        return javaMailProperties;
    }

    @Bean
    ImapIdleChannelAdapter mailAdapter() {

        ImapMailReceiver mailReceiver = new ImapMailReceiver(mailUrl + mailFolder);
        mailReceiver.setJavaMailProperties(javaMailProperties());
        mailReceiver.setShouldDeleteMessages(false);
        mailReceiver.setShouldMarkMessagesAsRead(true);
        mailReceiver.afterPropertiesSet();

        ImapIdleChannelAdapter imapIdleChannelAdapter = new
                ImapIdleChannelAdapter(mailReceiver);
        imapIdleChannelAdapter.setAutoStartup(true);
        imapIdleChannelAdapter.setOutputChannel(inputChannel());

        return imapIdleChannelAdapter;
    }

    @Bean
    public DirectChannel inputChannel() {
        return new DirectChannel();
    }
}
