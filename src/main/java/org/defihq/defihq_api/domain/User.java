package org.defihq.defihq_api.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
public class User {

    @NotNull
    @Id
    private String userName;

    @NotNull
    @Size(max = 255)
    private String userPassword;

    @NotNull
    private LocalDateTime created;

}
