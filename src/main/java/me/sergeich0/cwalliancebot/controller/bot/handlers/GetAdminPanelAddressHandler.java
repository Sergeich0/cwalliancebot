package me.sergeich0.cwalliancebot.controller.bot.handlers;

import me.sergeich0.cwalliancebot.controller.bot.util.BotCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class GetAdminPanelAddressHandler implements UpdateHandler {
    @Value("${admin-panel.url}")
    private String adminPanelUrl;

    @Value("${telegram.bot.username}")
    private String botName;

    private AbsSender telegramBot;


    @Override
    public void handle(Update update) {

        validate(update);


        BotApiMethod<Message> sendMessage = new SendMessage(update.getMessage().getChatId().toString(), adminPanelUrl);

        // Notify user
        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(sendMessage);
        }
    }

    private void validate(Update update) {
        if (update.hasMessage()
                && update.getMessage().hasText()
                && (update.getMessage().getText().equals(BotCommands.ADMIN_PANEL.getCommand())
                || update.getMessage().getText().equals(BotCommands.ADMIN_PANEL.getCommand() + "@" + botName))
        ) {
            return;
        }
        throw new WrongHandlerException("Not a POI!");
    }

    @Autowired
    public void setTelegramBot(@Lazy AbsSender telegramBot) {
        this.telegramBot = telegramBot;
    }

}
