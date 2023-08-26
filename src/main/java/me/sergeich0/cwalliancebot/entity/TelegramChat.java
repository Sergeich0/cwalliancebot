package me.sergeich0.cwalliancebot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TelegramChat {
    @Id
    @NonNull
    private Long id; // Telegram chat id

    @NonNull
    private String type; // Private, group, supergroup or channel

    private String title; // Name of chat

    private String firstName; // This and below fields is for user

    private String lastName;

    private String userName;

    private boolean active;

}
