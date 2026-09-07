package com.example.url_shortener.controller;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.service.UrlService;
import com.example.urlshortener.controller.UrlController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // New Mocking API

import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UrlController.class)
class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlService urlService;

    @Test
    void testGetByAlias() throws Exception {
        Url url = new Url();
        url.setUrl("https://example.com");
        url.setUrlAlias("example");

        Mockito.when(urlService.getByAlias("example"))
                .thenReturn(Optional.of(url));

        mockMvc.perform(get("/api/urls/example"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("https://example.com"))
                .andExpect(jsonPath("$.urlAlias").value("example"));
    }
}
