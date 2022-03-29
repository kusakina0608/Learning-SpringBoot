package com.kina.guestbook.service;

import com.kina.guestbook.dto.GuestbookDto;
import com.kina.guestbook.dto.PageRequestDto;
import com.kina.guestbook.dto.PageResultDto;
import com.kina.guestbook.entity.Guestbook;
import com.kina.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

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

    @Override
    public GuestbookDto read(Long gno) {

        Optional<Guestbook> result = repository.findById(gno);

        return result.isPresent() ? entityToDto(result.get()) : null;

    }

    @Override
    public PageResultDto<GuestbookDto, Guestbook> getList(PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPagable(Sort.by("gno"));

        Page<Guestbook> result = repository.findAll(pageable);

        Function<Guestbook, GuestbookDto> fn = this::entityToDto;

        return new PageResultDto<>(result, fn);

    }

    @Override
    public void remove(Long gno) {

        repository.deleteById(gno);

    }

    @Override
    public void modify(GuestbookDto dto) {

        Optional<Guestbook> result = repository.findById(dto.getGno());

        if (result.isPresent()) {

            Guestbook entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repository.save(entity);

        }

    }

}
