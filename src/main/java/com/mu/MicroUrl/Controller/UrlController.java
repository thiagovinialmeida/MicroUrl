package com.mu.MicroUrl.Controller;

import com.mu.MicroUrl.DTO.RequestDTO;
import com.mu.MicroUrl.DTO.UrlDTO;
import com.mu.MicroUrl.Service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping
    public ResponseEntity<List<UrlDTO>> getAllUrls() {
        return urlService.getAllUrls();
    }

    @GetMapping(value = "info/{url}")
    public ResponseEntity<UrlDTO> getUrlByUrl (@PathVariable String url) {
        return ResponseEntity.ok(urlService.getUrlByUrl(url));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Void> redirect(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.FOUND).headers(urlService.redirect(id)).build();
    }

    @PostMapping
    public ResponseEntity<RequestDTO> shortenUrl(@RequestBody RequestDTO url, HttpServletRequest servletRequest) {
        return urlService.getShortUrl(url, servletRequest);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUrl(@PathVariable UUID id) {
        urlService.DeleteUrl(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
