package br.com.prcompany.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("kebab")
@JsonTest
public class BeerDTOKebabTest extends BaseTest {

    @Test
    void testSerializeDTO() throws JsonProcessingException {
        BeerDTO beerDTO = super.getDTO();

        String jsonString = this.objectMapper.writeValueAsString(beerDTO);

        log.info(jsonString);

    }

    @Test
    void testDeserialize() throws JsonProcessingException {
        String json = "{\"id\":\"6ff5c25e-e9ad-404e-9d92-1de5987313fd\",\"version\":null,\"created-date\":\"2021-04-07T14:48:38.2021803-03:00\",\"last-modified-date\":\"2021-04-07T14:48:38.2021803-03:00\",\"beer-name\":\"Name\",\"beer-style\":\"ALE\",\"upc\":12123123,\"price\":12.99,\"quantity-on-hand\":null}";

        BeerDTO dto = this.objectMapper.readValue(json, BeerDTO.class);

        log.info(dto.toString());
    }
}
