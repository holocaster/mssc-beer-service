package br.com.prcompany.msscbeerservice.web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

class BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    BeerDTO getDTO() {
        return BeerDTO.builder().beerName("Name").beerStyle(BeerStyleEnum.ALE)
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal("12.99"))
                .upc(12123123L)
                .build();
    }
}