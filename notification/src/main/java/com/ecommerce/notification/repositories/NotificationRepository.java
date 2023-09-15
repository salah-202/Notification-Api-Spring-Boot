package com.ecommerce.notification.repositories;

import com.ecommerce.notification.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> getByEmailStatus(String status);
}
