package com.ijobhawk.jobhawk.config;

import com.ijobhawk.jobhawk.config.properties.PlaywrightProperties;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PlaywrightProperties.class)
public class PlaywrightConfig {

    @Bean(destroyMethod = "close")
    Playwright playwright(){
        return Playwright.create();
    }

    @Bean(destroyMethod = "close")
    Browser browser(Playwright playwright, PlaywrightProperties props){
        return playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(props.headless())
                .setSlowMo(props.slowMoMs())
        );
    }
}
