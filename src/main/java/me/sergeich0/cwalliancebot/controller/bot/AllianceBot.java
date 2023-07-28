package me.sergeich0.cwalliancebot.controller.bot;

import me.sergeich0.cwalliancebot.controller.bot.handlers.UpdateHandler;
import me.sergeich0.cwalliancebot.controller.bot.handlers.WrongHandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;


public class AllianceBot extends TelegramWebhookBot {

    private String botUsername;
    private String botPath;

    private List<UpdateHandler> updateHandlers;

    public static AllianceBotBuilder builder() {
        return new AllianceBotBuilder();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (updateHandlers != null) {
            for (UpdateHandler updateHandler : updateHandlers) {
                try {
                    updateHandler.handle(update);
                } catch (WrongHandlerException e) {
                    // skip
                }
            }
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return this.botPath;
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    public AllianceBot(String botToken, String botUsername, String botPath) {
        super(botToken);
        this.botUsername = botUsername;
        this.botPath = botPath;
    }

    @Autowired
    public void setUpdateHandlers(List<UpdateHandler> updateHandlers) {
        this.updateHandlers = updateHandlers;
    }

    public static class AllianceBotBuilder {
        private String botToken;
        private String botUsername;
        private String botPath;
        private List<UpdateHandler> updateHandlers;

        AllianceBotBuilder() {
        }

        public AllianceBotBuilder botToken(String botToken) {
            this.botToken = botToken;
            return this;
        }

        public AllianceBotBuilder botUsername(String botUsername) {
            this.botUsername = botUsername;
            return this;
        }

        public AllianceBotBuilder botPath(String botPath) {
            this.botPath = botPath;
            return this;
        }

        public AllianceBotBuilder updateHandlers(List<UpdateHandler> updateHandlers) {
            this.updateHandlers = updateHandlers;
            return this;
        }

        public AllianceBot build() {
            return new AllianceBot(this.botToken, this.botUsername, this.botPath);
        }

        public String toString() {
            return "AllianceBot.AllianceBotBuilder(botUsername=" + this.botUsername + ", botPath=" + this.botPath + ", updateHandlers=" + this.updateHandlers + ")";
        }
    }
}
