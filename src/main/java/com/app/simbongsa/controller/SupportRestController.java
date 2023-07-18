package com.app.simbongsa.controller;

import com.app.simbongsa.domain.SupportDTO;
import com.app.simbongsa.service.support.SupportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supports/*")
@RequiredArgsConstructor
@Slf4j
public class SupportRestController {
}

