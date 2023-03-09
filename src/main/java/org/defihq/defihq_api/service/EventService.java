package org.defihq.defihq_api.service;

import java.util.UUID;
import java.util.stream.Collectors;
import org.defihq.defihq_api.domain.Event;
import org.defihq.defihq_api.model.EventDTO;
import org.defihq.defihq_api.model.SimplePage;
import org.defihq.defihq_api.repos.EventRepository;
import org.defihq.defihq_api.util.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(final EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public SimplePage<EventDTO> findAll(final String filter, final Pageable pageable) {
        Page<Event> page;
        if (filter != null) {
            UUID uuidFilter = null;
            try {
                uuidFilter = UUID.fromString(filter);
            } catch (final IllegalArgumentException illegalArgumentException) {
                // keep null - no parseable input
            }
            page = eventRepository.findAllById(uuidFilter, pageable);
        } else {
            page = eventRepository.findAll(pageable);
        }
        return new SimplePage<>(page.getContent()
                .stream()
                .map((event) -> mapToDTO(event, new EventDTO()))
                .collect(Collectors.toList()),
                page.getTotalElements(), pageable);
    }

    public EventDTO get(final UUID id) {
        return eventRepository.findById(id)
                .map(event -> mapToDTO(event, new EventDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final EventDTO eventDTO) {
        final Event event = new Event();
        mapToEntity(eventDTO, event);
        return eventRepository.save(event).getId();
    }

    public void update(final UUID id, final EventDTO eventDTO) {
        final Event event = eventRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(eventDTO, event);
        eventRepository.save(event);
    }

    public void delete(final UUID id) {
        eventRepository.deleteById(id);
    }

    private EventDTO mapToDTO(final Event event, final EventDTO eventDTO) {
        eventDTO.setId(event.getId());
        eventDTO.setTimestamp(event.getTimestamp());
        eventDTO.setUserName(event.getUserName());
        eventDTO.setCategory(event.getCategory());
        eventDTO.setEvent(event.getEvent());
        eventDTO.setResult(event.getResult());
        return eventDTO;
    }

    private Event mapToEntity(final EventDTO eventDTO, final Event event) {
        event.setTimestamp(eventDTO.getTimestamp());
        event.setUserName(eventDTO.getUserName());
        event.setCategory(eventDTO.getCategory());
        event.setEvent(eventDTO.getEvent());
        event.setResult(eventDTO.getResult());
        return event;
    }

}
