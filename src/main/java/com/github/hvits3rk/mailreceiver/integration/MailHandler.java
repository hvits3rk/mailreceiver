package com.github.hvits3rk.mailreceiver.integration;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@MessageEndpoint
public class MailHandler {

    @ServiceActivator(inputChannel = "inputChannel")
    public void processMessage(MimeMessage message) throws MessagingException, IOException {
        System.out.println("########################");
        System.out.println(message.getSubject());
        System.out.println("########################");

        Folder folder = message.getFolder();
        folder.open(Folder.READ_WRITE);

        Multipart multipart = (Multipart) message.getContent();
        for (int i = 0; i < multipart.getCount(); i++) {

            BodyPart bodyPart = multipart.getBodyPart(i);
            String disposition = bodyPart.getDisposition();

            if (disposition != null && Part.ATTACHMENT.equalsIgnoreCase(disposition)) {
                System.out.println("Mail has some attachments");
                DataHandler handler = bodyPart.getDataHandler();
                System.out.println("file name : " + handler.getName());
                ((MimeBodyPart) bodyPart).saveFile("/home/hvits3rk/Downloads/received_files/" + bodyPart.getFileName());
//                InputStream is = bodyPart.getInputStream();
//                File f = new File("/home/hvits3rk/Downloads/received_files/" + bodyPart.getFileName());
//                FileOutputStream fos = new FileOutputStream(f);
//                byte[] buf = new byte[4096];
//                int bytesRead;
//                while ((bytesRead = is.read(buf)) != -1) {
//                    fos.write(buf, 0, bytesRead);
//                }
//                fos.close();
            } else {
                System.out.println("Body: " + bodyPart.getContent());
            }
        }

        folder.close(true);
    }

//        Multipart multipart = (Multipart) message.getContent();
//
//        for (int i = 0; i < multipart.getCount(); i++) {
//            BodyPart bodyPart = multipart.getBodyPart(i);
//
//            if(!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) &&
//                    StringUtils.isBlank(bodyPart.getFileName())) {
//                continue; // dealing with attachments only
//            }
//            InputStream is = bodyPart.getInputStream();
//            File f = new File("/tmp/" + bodyPart.getFileName());
//            FileOutputStream fos = new FileOutputStream(f);
//            byte[] buf = new byte[4096];
//            int bytesRead;
//            while((bytesRead = is.read(buf))!=-1) {
//                fos.write(buf, 0, bytesRead);
//            }
//            fos.close();
//            attachments.add(f);
//        }
}
