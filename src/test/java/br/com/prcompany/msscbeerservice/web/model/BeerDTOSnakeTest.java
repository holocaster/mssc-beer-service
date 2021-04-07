package br.com.prcompany.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("snake")
@JsonTest
public class BeerDTOSnakeTest extends BaseTest {

    @Test
    void testSerializeDTO() throws JsonProcessingException {
        BeerDTO beerDTO = super.getDTO();

        String jsonString = this.objectMapper.writeValueAsString(beerDTO);

        log.info(jsonString);

    }

    @Test
    void testDeserialize() throws JsonProcessingException {
        String json = "{\"id\":\"1388f9a9-5dab-483a-8b17-77e69738d63b\",\"version\":null,\"created_date\":\"2021-04-07T14:44:20.2146505-03:00\",\"last_modified_date\":\"2021-04-07T14:44:20.2146505-03:00\",\"beer_name\":\"Name\",\"beer_style\":\"ALE\",\"upc\":12123123,\"price\":12.99,\"quantity_on_hand\":null}";

        BeerDTO dto = this.objectMapper.readValue(json, BeerDTO.class);

        log.info(dto.toString());
    }
}
