package org.defihq.defihq_api.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
public class Event {

    @Id
    private UUID id;

    private LocalDateTime timestamp;

    @Size(max = 255)
    private String userName;

    @NotNull
    @Size(max = 255)
    private String category;

    @NotNull
    @Size(max = 255)
    private String event;

    @Size(max = 255)
    private String result;

}
