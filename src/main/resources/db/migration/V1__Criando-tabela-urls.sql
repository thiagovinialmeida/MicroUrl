﻿CREATE TABLE url (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    shortUrl VARCHAR(10) NOT NULL,
    originalUrl VARCHAR(255) NOT NULL,
    clicks INT NOT NULL DEFAULT 0
);
