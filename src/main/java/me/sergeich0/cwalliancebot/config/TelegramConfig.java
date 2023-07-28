package me.sergeich0.cwalliancebot.config;

import lombok.Getter;
import me.sergeich0.cwalliancebot.controller.bot.AllianceBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;

@Component
@Getter
public class TelegramConfig {

    private final ApplicationContext applicationContext;

    @Value("${telegram.bot.token}")
    String botToken;

    @Value("${telegram.bot.username}")
    String botUsername;

    @Value("${telegram.bot.admin}")
    String adminId;

    @Value("${telegram.chatwars.id}")
    String chatWarsId;

    @Value("${telegram.webhook.url}")
    String webhookUrl;

    @Value("${telegram.certificate}")
    String certificatePath;


    @Bean
    SetWebhook setWebhook() throws IOException {
        InputStream inputStream = new ClassPathResource(certificatePath).getInputStream();
        InputFile certificate = new InputFile(inputStream, certificatePath);
        return SetWebhook.builder()
                .url(webhookUrl)
                .certificate(certificate)
                .build();
    }

    @Bean
    AllianceBot allianceBot() throws TelegramApiException {
        SetWebhook setWebhook = applicationContext.getBean("setWebhook", SetWebhook.class);
        AllianceBot bot = AllianceBot.builder()
                .botToken(botToken)
                .botUsername(botUsername)
                .build();
        bot.setWebhook(setWebhook);
        return bot;
    }

    @Autowired
    public TelegramConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
