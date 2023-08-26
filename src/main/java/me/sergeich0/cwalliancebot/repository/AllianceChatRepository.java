package me.sergeich0.cwalliancebot.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import me.sergeich0.cwalliancebot.entity.TelegramChat;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class AllianceChatRepository implements ChatRepository {

    private EntityManager entityManager;
    @Override
    public void save(TelegramChat chat) {
        entityManager.merge(chat);
    }

    @Override
    public TelegramChat getChat(Long id) {
        return entityManager.find(TelegramChat.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TelegramChat> getAllChats() {
        Query query = entityManager.createQuery("from TelegramChat where active=true",
                AlliancePoiRepository.class);

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TelegramChat> getInactiveChats() {
        Query query = entityManager.createQuery("from TelegramChat where active=false",
                AlliancePoiRepository.class);

        return query.getResultList();
    }
}
