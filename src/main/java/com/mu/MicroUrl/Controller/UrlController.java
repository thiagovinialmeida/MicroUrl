package com.mu.MicroUrl.Controller;

import com.mu.MicroUrl.DTO.RequestDTO;
import com.mu.MicroUrl.DTO.UrlDTO;
import com.mu.MicroUrl.Domain.Url;
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
    public ResponseEntity<List<Url>> getAllUrls() {
        return ResponseEntity.ok(urlService.getAllUrls());
    }

    @GetMapping(value = "origin/{id}")
    public ResponseEntity<UrlDTO> getUrlById(@PathVariable UUID id) {
        return ResponseEntity.ok(urlService.getUrlById(id));
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
