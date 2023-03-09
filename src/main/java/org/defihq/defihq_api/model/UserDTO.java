package org.defihq.defihq_api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    @Size(max = 255)
    private String userName;

    @NotNull
    @Size(max = 255)
    private String userPassword;

    @NotNull
    private LocalDateTime created;

}
