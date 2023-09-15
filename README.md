# Notification Service for E-Commerce

## About

The Notification Service is a crucial component of our E-Commerce platform, responsible for sending order-related notifications to customers and merchants. It utilizes RabbitMQ for message queuing and Spring Boot for email handling, ensuring timely and reliable communication.

## Features

- **Order Notification**: Listens for newly created orders via RabbitMQ and extracts customer and merchant emails and order details.

- **Email Delivery**: Utilizes Spring Boot Starter Mail and JavaMailSender to send email notifications to customers and merchants.

- **Reliability**: Monitors email delivery status and stores notifications in the database, allowing for tracking and error handling.

- **Retry Mechanism**: Automatically retries sending failed emails every 24 hours, up to three attempts, ensuring critical notifications reach their recipients.

## Usage

1. **Order Notification**

   - When an order is created, the service listens for the event via RabbitMQ.
   - It extracts customer and merchant emails along with order details for notification.

2. **Email Delivery**

   - The service uses Spring Boot Starter Mail and JavaMailSender to send notifications.
   - It captures delivery status and stores the notification in the database.

3. **Retry Mechanism**

   - If an email fails to send, the service automatically retries every 24 hours.
   - Retries are attempted up to three times before the notification is marked as terminated.

## Architecture

The Notification Service is designed with a focus on reliability and scalability. It leverages RabbitMQ for asynchronous message processing, Spring Boot for email handling, and a robust database for storing notifications.

## Database

Notifications and delivery status are stored in a database for tracking and analysis. The database schema and interactions are managed using Liquibase.

