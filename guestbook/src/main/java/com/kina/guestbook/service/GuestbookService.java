package com.kina.guestbook.service;

import com.kina.guestbook.dto.GuestbookDto;
import com.kina.guestbook.dto.PageRequestDto;
import com.kina.guestbook.dto.PageResultDto;
import com.kina.guestbook.entity.Guestbook;

public interface GuestbookService {

    Long register(GuestbookDto dto);

    PageResultDto<GuestbookDto, Guestbook> getList(PageRequestDto requestDto);

    GuestbookDto read(Long gno);

    default Guestbook dtoToEntity(GuestbookDto dto) {
        return Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
    }

    default GuestbookDto entityToDto(Guestbook entity) {

        return GuestbookDto.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

    }

}
