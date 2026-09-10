//package com.example.urlshortener.controller;
//
//
//import com.example.urlshortener.dto.ShortenRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class UrlControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    @Test
//    void shortenUrl_createsShortUrl() throws Exception {
//        ShortenRequest req = new ShortenRequest();
//        req.setFullUrl("https://example.com/very/long/url");
//        req.setCustomAlias("my-custom-alias");
//
//        mockMvc.perform(post("/shorten")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(req)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.shortUrl").value("http://localhost:8080/my-custom-alias"));
//    }
//
//    @Test
//    void redirect_aliasFound_returns302() throws Exception {
//        ShortenRequest req = new ShortenRequest();
//        req.setFullUrl("https://example.com");
//        req.setCustomAlias("abc123");
//
//        mockMvc.perform(post("/shorten")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(req)))
//                .andExpect(status().isCreated());
//
//        mockMvc.perform(get("/abc123"))
//                .andExpect(status().isFound())
//                .andExpect(header().string("Location", "https://example.com"));
//    }
//
//    @Test
//    void redirect_aliasNotFound_returns404() throws Exception {
//        mockMvc.perform(get("/does-not-exist"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void delete_aliasFound_returns204() throws Exception {
//        ShortenRequest req = new ShortenRequest();
//        req.setFullUrl("https://example.com/delete");
//        req.setCustomAlias("delete-me");
//
//        mockMvc.perform(post("/shorten")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(req)))
//                .andExpect(status().isCreated());
//
//        mockMvc.perform(delete("/delete-me"))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void delete_aliasNotFound_returns404() throws Exception {
//        mockMvc.perform(delete("/missing-alias"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void listUrls_returns200AndList() throws Exception {
//        ShortenRequest req = new ShortenRequest();
//        req.setFullUrl("https://example.com/list");
//        req.setCustomAlias("list-alias");
//
//        mockMvc.perform(post("/shorten")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(req)))
//                .andExpect(status().isCreated());
//
//        mockMvc.perform(get("/urls"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].alias").value("list-alias"))
//                .andExpect(jsonPath("$[0].fullUrl").value("https://example.com/list"))
//                .andExpect(jsonPath("$[0].shortUrl").value("http://localhost:8080/list-alias"));
//    }
//}
