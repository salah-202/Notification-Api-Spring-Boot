package com.ecommerce.notification.services;

import com.ecommerce.notification.entities.Notification;
import com.ecommerce.notification.entities.NotificationModel;
import com.ecommerce.notification.repositories.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${rabbitMQ.queue.name}")
    public void dequeueNotification(NotificationModel notificationModel) {
        String customerEmail = notificationModel.getCustomerEmail();
        String merchantEmail = notificationModel.getMerchantEmail();
        String message = notificationModel.getMessage();

        Notification customerNotification = new Notification(customerEmail, message);
        Notification merchantNotification = new Notification(merchantEmail, message);

        sendNotification(customerNotification);
        sendNotification(merchantNotification);
    }

    public void sendNotification(Notification notification){
        try{
            emailService.sendEmail(notification.getEmailTo(), notification.getMessage());
            notification.setEmailStatus("Success");
        }catch (MailException ex){
            notification.setEmailStatus("Failed");
            notification.increaseRetries();
        }finally {
            saveNotification(notification);
        }
    }

    public void saveNotification(Notification notification){
        notificationRepository.save(notification);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resendNotification(){
        List<Notification> failedNotifications = notificationRepository.getByEmailStatus("Failed");
        for (Notification notification:failedNotifications) {
            if(notification.getRetries() >= 3){
                notification.setEmailStatus("Terminated");
                saveNotification(notification);
            }else {
                sendNotification(notification);
            }
        }
    }

}