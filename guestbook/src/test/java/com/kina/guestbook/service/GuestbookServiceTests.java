package com.kina.guestbook.service;

import com.kina.guestbook.dto.GuestbookDto;
import com.kina.guestbook.dto.PageRequestDto;
import com.kina.guestbook.dto.PageResultDto;
import com.kina.guestbook.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService service;

    @Test
    void testRegister() {

        GuestbookDto guestbookDto = GuestbookDto.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDto));

    }

    @Test
    void testList() {

        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDto<GuestbookDto, Guestbook> resultDto = service.getList(pageRequestDto);

        resultDto.getDtoList().forEach(System.out::println);

    }

}
