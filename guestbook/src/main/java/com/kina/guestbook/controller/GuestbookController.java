package com.kina.guestbook.controller;

import com.kina.guestbook.dto.PageRequestDto;
import com.kina.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@Slf4j
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService service;

    @GetMapping("/")
    public String list() {

        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model) {

        log.info("list....................");

        model.addAttribute("result", service.getList(pageRequestDto));

    }

}
