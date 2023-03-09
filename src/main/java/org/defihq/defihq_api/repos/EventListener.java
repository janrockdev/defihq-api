package org.defihq.defihq_api.repos;

import java.util.UUID;
import org.defihq.defihq_api.domain.Event;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class EventListener extends AbstractMongoEventListener<Event> {

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Event> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(UUID.randomUUID());
        }
    }

}
