package org.defihq.defihq_api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventDTO {

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
