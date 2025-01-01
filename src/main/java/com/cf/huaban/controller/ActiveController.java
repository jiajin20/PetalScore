package com.cf.huaban.controller;

import com.cf.huaban.dto.ActiveDto;
import com.cf.huaban.entity.Active;
import com.cf.huaban.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("actives")
public class ActiveController {
    @Autowired
    private ActiveService activeService;
    @GetMapping("/active")
    public ResponseEntity<ActiveDto> findActive() {
        try {
            Active active = activeService.active();
            String imgurls = active.getImg();
            List<String> imageList = null;
            if (imgurls != null && !imgurls.trim().isEmpty()) {
                imageList = Arrays.asList(imgurls.split(","));
            }
            ActiveDto activeResponse = new ActiveDto(active, imageList);
            return ResponseEntity.ok(activeResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
