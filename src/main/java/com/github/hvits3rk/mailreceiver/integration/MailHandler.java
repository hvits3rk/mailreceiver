package com.github.hvits3rk.mailreceiver.integration;

import com.github.hvits3rk.mailreceiver.service.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@MessageEndpoint
public class MailHandler {

    private final SparkService sparkService;

    @Autowired
    public MailHandler(SparkService sparkService) {
        this.sparkService = sparkService;
    }

    @ServiceActivator(inputChannel = "inputChannel")
    public void processMessage(MimeMessage message) throws MessagingException, IOException {
        Folder folder = message.getFolder();
        folder.open(Folder.READ_WRITE);

        Multipart multipart = (Multipart) message.getContent();
        String fromHeader = message.getHeader("From")[0];

        for (int i = 0; i < multipart.getCount(); i++) {
            BodyPart bodyPart = multipart.getBodyPart(i);
            String disposition = bodyPart.getDisposition();

            if (disposition != null && Part.ATTACHMENT.equalsIgnoreCase(disposition)) {
                String filename = bodyPart.getFileName();
                if (!filename.endsWith(".csv"))
                    continue;
                Path newFilePath = Paths.get("/home/hvits3rk/Downloads/received_files/" + filename);
                Files.createFile(newFilePath);
                ((MimeBodyPart) bodyPart).saveFile(newFilePath.toFile());

                sparkService.runCsvDataFrame(newFilePath.toString(), fromHeader);
            }
        }

        folder.close(true);
    }

}
