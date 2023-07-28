package me.sergeich0.cwalliancebot.controller.bot.handlers;

import lombok.RequiredArgsConstructor;
import me.sergeich0.cwalliancebot.controller.bot.util.PoiType;
import me.sergeich0.cwalliancebot.dto.AlliancePointOfInterestDTO;
import me.sergeich0.cwalliancebot.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@RequiredArgsConstructor
public class NewPoiHandler implements UpdateHandler {

    @Value("${telegram.chatwars.id}")
    private Long chatWarsId;

    private final PoiService poiService;

    private AbsSender telegramBot;

    @Override
    public void handle(Update update) {
        validate(update);

        AlliancePointOfInterestDTO poi = parsePoi(update);

        BotApiMethod<Message> sendMessage;
        try {
            poiService.save(poi);
            sendMessage = new SendMessage(update.getMessage().getChatId().toString(),
                    "POI " + poi.getCode() + " received!");
        } catch (DataIntegrityViolationException e) { // code must be unique
            sendMessage = new SendMessage(update.getMessage().getChatId().toString(),
                    "POI already exist!");
        }

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
                && update.getMessage().getForwardFrom() != null
                && update.getMessage().getForwardFrom().getId().equals(chatWarsId)
                && update.getMessage().getText().contains("with simple combination")
        ) {
            return;
        }
        throw new WrongHandlerException("Not a POI!");
    }

    private AlliancePointOfInterestDTO parsePoi(Update update) {
        // parse values
        String messageText = update.getMessage().getText();
        String poiCode = parsePoiCode(messageText);
        PoiType poiType = parsePoiType(messageText);
        Integer poiLevel = null;
        if (poiType == PoiType.LOCATION) {
            poiLevel = parsePoiLevel(messageText);
        }
        String poiName = parsePoiName(messageText, poiType);
        return new AlliancePointOfInterestDTO(poiCode, poiType, poiName, poiLevel);
    }

    private String parsePoiCode(String text) {
        Pattern pattern = Pattern.compile("with simple combination: (\\w+)\\b");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find()) {
            throw new WrongHandlerException("There is no code! Check regex pattern");
        }
        return matcher.group(1);
    }

    private PoiType parsePoiType(String text) {
        Pattern pattern = Pattern.compile("You found hidden (location|headquarter)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find()) {
            throw new WrongHandlerException("Unknown POI type!");
        }
        String type = matcher.group(1);
        return PoiType.getByType(type);
    }


    private String parsePoiName(String text, PoiType poiType) {
        Pattern pattern;
        if (poiType == PoiType.LOCATION) {
            pattern = Pattern.compile("You found hidden location ([\\w\\s]+) lvl\\.");
        } else {
            pattern = Pattern.compile("You found hidden headquarter ([\\w\\s]+)\\b");
        }
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find()) {
            throw new WrongHandlerException("There is no name!");
        }
        return matcher.group(1);
    }

    private Integer parsePoiLevel(String text) {
        Pattern pattern = Pattern.compile("You found hidden location ([\\w\\s]+) lvl.(\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find()) {
            throw new WrongHandlerException("There is no level!");
        }
        return Integer.parseInt(matcher.group(2));
    }

    @Autowired
    public void setTelegramBot(@Lazy AbsSender telegramBot) {
        this.telegramBot = telegramBot;
    }

}
