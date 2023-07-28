# Chat Wars Alliance bot
## Description
This app was created to help alliance commanders take care of alliance battles 
in [ChatWars](https://t.me/chtwrsbot)

Bot can consume POIs either from groups and from DMs.

It uses a webhooks with self-signed certificate and requires static IP or domain name.

## Preparation

To use app you will need:
- Installed Java SDK v17 or higher
- Installed MySQL Server v8
- Installed OpenSSL
- Bot token from [@BotFather](https://t.me/BotFather) and bot username

First, create database with **initial.sql**.

Second, create **keystore.p12** and **certificate.pem** following 
instruction [here](https://core.telegram.org/bots/self-signed#java-keystore). 
Put them in a src/main/resources.

After all, rename **application.properties.example** to **application.properties** 
and fill all the properties in **application.properties**.

## Launch

Depends on your platform use `./gradlew bootRun` or `.\gradlew.bat bootRun`
