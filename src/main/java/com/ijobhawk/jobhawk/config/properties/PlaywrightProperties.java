package com.ijobhawk.jobhawk.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "jobhawk.playwright")
public record PlaywrightProperties(
        boolean headless,
        double slowMoMs,
        double timeoutMs
) {}
