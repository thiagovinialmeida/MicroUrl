package com.mu.MicroUrl.Service;

import com.mu.MicroUrl.DTO.RequestDTO;
import com.mu.MicroUrl.DTO.ShortedUrlInfo;
import com.mu.MicroUrl.DTO.UrlDTO;
import com.mu.MicroUrl.Domain.Url;
import com.mu.MicroUrl.Infrastructure.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    @Value("${app.domain}")
    private String domain;

    public ResponseEntity<RequestDTO> getShortUrl(RequestDTO originalUrl, HttpServletRequest servletRequest) {
        if(originalUrl.requestUrl().isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else if(urlRepository.findUrlByOriginalUrl(originalUrl.requestUrl()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new RequestDTO("This URL has already been shortened: "
                                + originalUrl.requestUrl()));
        } else {
            Url url = new Url();
            String shortUrl = toSha256(originalUrl.requestUrl());
            url.setShortUrl(shortUrl.substring(0,10));
            url.setOriginalUrl(originalUrl.requestUrl());
            url.setClicks(0);
            urlRepository.save(url);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RequestDTO(servletRequest
                    .getRequestURL()
                    .toString() + "/" + shortUrl.substring(0,10)));
        }
    }
    public ResponseEntity<List<UrlDTO>> getAllUrls() {
        List<UrlDTO> respUrl = new ArrayList<>();
        for(var url : urlRepository.findAll()) {
            UrlDTO dto = new UrlDTO(url.getShortUrl(), url.getOriginalUrl(), url.getClicks());
            respUrl.add(dto);
        }
        return ResponseEntity.ok(respUrl);
    }
    public UrlDTO getUrlByUrl(ShortedUrlInfo data){
        List<String> parts = List.of(data.url().split("/"));
        Url url = urlRepository.findUrlByShortUrl(parts.getLast());
        return new UrlDTO(domain + "/url/" + url.getShortUrl(), url.getOriginalUrl(), url.getClicks());
    }
    public HttpHeaders redirect(String shortenUrl) {
        Url url = urlRepository.findUrlByShortUrl(shortenUrl);
        url.setClicks(url.getClicks() + 1);
        urlRepository.save(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getOriginalUrl()));
        return headers;
    }
    public void DeleteUrl(String data) {
        urlRepository.delete(urlRepository.findUrlByShortUrl(data));
    }

    private String toSha256(String originalUrl) {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA256");
            byte[] bytes = md.digest(originalUrl.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for(byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        }
        catch (Exception e) {
            throw new RuntimeException("Error generating short URL: " + e.getMessage());
        }
    }
    private Url findByUrl(UUID id) {
        return urlRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("URL not found with id: " + id));
    }
}
