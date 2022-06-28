package fontys.sem3.hpfapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String senderEmail;

    private String receiverEmail;

    private String content;
}
