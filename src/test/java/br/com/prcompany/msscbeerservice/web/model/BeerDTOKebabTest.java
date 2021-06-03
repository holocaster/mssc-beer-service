package br.com.prcompany.msscbeerservice.web.model;

import br.com.prcompany.beerevents.model.BeerDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
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
        String json = "{\"id\":\"7bc51a83-1d31-4a05-a874-2a189f9d0318\",\"version\":null,\"created-date\":\"2021-04-08T08:52:00-0300\",\"last-modified-date\":\"2021-04-08T08:52:00-0300\",\"beer-name\":\"Name\",\"beer-style\":\"ALE\",\"upc\":12123123,\"price\":\"12.99\",\"quantity-on-hand\":null}";

        BeerDTO dto = this.objectMapper.readValue(json, BeerDTO.class);

        log.info(dto.toString());
    }
}
