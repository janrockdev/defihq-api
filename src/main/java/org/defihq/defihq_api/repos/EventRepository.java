package org.defihq.defihq_api.repos;

import java.util.UUID;
import org.defihq.defihq_api.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EventRepository extends MongoRepository<Event, UUID> {

    Page<Event> findAllById(UUID id, Pageable pageable);

}
