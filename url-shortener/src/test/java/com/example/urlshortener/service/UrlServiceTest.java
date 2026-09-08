//package com.example.urlshortener.service;
//
//import com.example.urlshortener.model.Url;
//import com.example.urlshortener.repository.UrlRepository;
//import com.example.urlshortener.service.UrlService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class UrlServiceTest {
//
//    private final UrlRepository urlRepository = Mockito.mock(UrlRepository.class);
//    private final UrlService urlService = new UrlService(urlRepository);
//
//    @Test
//    void testCreateUrl() {
//        Url url = new Url();
//        url.setUrl("https://example.com");
//        url.setUrlAlias("example");
//        url.setUrlTitle("Example");
//
//        Mockito.when(urlRepository.save(Mockito.any())).thenReturn(url);
//
//        Url created = urlService.create("https://example.com", "example", "Example");
//
//        assertThat(created.getUrl()).isEqualTo("https://example.com");
//        assertThat(created.getUrlAlias()).isEqualTo("example");
//    }
//
//    @Test
//    void testGetByAlias() {
//        Url url = new Url();
//        url.setUrlAlias("abc");
//
//        Mockito.when(urlRepository.findByUrlAlias("abc"))
//                .thenReturn(Optional.of(url));
//
//        Optional<Url> found = urlService.getByAlias("abc");
//
//        assertThat(found).isPresent();
//        assertThat(found.get().getUrlAlias()).isEqualTo("abc");
//    }
//}
