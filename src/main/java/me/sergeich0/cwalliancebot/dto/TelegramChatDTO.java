package me.sergeich0.cwalliancebot.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TelegramChatDTO {

    private Long id;
    private String type;
    private String title;
    private String firstName;
    private String lastName;
    private String userName;
    @Builder.Default
    private boolean active = true;

}
