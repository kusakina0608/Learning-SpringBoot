package com.kina.guestbook.controller;

import com.kina.guestbook.dto.GuestbookDto;
import com.kina.guestbook.dto.PageRequestDto;
import com.kina.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/register")
    public void register() {

        log.info("register get...");

    }

    @PostMapping("/register")
    public String registerPost(GuestbookDto dto, RedirectAttributes redirectAttributes) {

        log.info("dto..." + dto);

        Long gno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";

    }

    @GetMapping("/read")
    public void read(long gno, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {

        log.info("gno: " + gno);

        GuestbookDto dto = service.read(gno);

        model.addAttribute("dto", dto);

    }

}
