package com.kina.guestbook.service;

import com.kina.guestbook.dto.GuestbookDto;
import com.kina.guestbook.entity.Guestbook;
import com.kina.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository;

    @Override
    public Long register(GuestbookDto dto) {

        log.info("DTO--------------------");
        log.info(dto.toString());

        Guestbook entity = dtoToEntity(dto);

        log.info(entity.toString());

        repository.save(entity);

        return entity.getGno();

    }

}
