package com.ecommerce.notification.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@NoArgsConstructor
@Getter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email_from")
    private final String EMAIL_FROM = "shop@shop.com";
    @Column(name = "email_to")
    private String emailTo;
    @Column(name = "message")
    private String message;
    @Column(name = "email_status")
    private String emailStatus;
    @Column(name = "retries")
    private int retries = 0;

    public Notification(String emailTo,String message){
        this.emailTo = emailTo;
        this.message = message;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public void increaseRetries() {
        this.retries++;
    }
}
