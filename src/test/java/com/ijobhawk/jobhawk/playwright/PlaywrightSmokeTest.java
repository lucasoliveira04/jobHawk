package com.ijobhawk.jobhawk.playwright;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PlaywrightSmokeTest {
    private static Playwright playwright;
    private static Browser browser;

    @BeforeAll
    static void abrirBrowser() {
        playwright = Playwright.create(new Playwright.CreateOptions()
                .setEnv(Map.of("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1")));
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @AfterAll
    static void fecharBrowser() {
        browser.close();
        playwright.close();
    }

    @Test
    void deveAbrirPaginaEEncontrarVagas() {
        try (BrowserContext context = browser.newContext()) {
            Page page = context.newPage();
            page.navigate("https://realpython.github.io/fake-jobs/");

            List<String> cards = page.locator(".card-content").allInnerTexts();

            assertThat(page.title()).isEqualTo("Fake Python");
            assertThat(cards).hasSize(100);
            assertThat(cards.get(0)).contains("Senior Python Developer");
        }
    }
}
