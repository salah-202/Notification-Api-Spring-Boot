package com.ecommerce.notification.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NotificationModel {
    private String customerEmail;
    private String merchantEmail;
    private String message;

}
