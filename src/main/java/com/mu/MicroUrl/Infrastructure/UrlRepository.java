package com.mu.MicroUrl.Infrastructure;

import com.mu.MicroUrl.Domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UrlRepository extends JpaRepository<Url, UUID>{ }
