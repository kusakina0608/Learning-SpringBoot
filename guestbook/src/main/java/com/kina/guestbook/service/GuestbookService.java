package com.kina.guestbook.service;

import com.kina.guestbook.dto.GuestbookDto;
import com.kina.guestbook.entity.Guestbook;

public interface GuestbookService {

    Long register(GuestbookDto dto);

    default Guestbook dtoToEntity(GuestbookDto dto) {
        return Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
    }
}
