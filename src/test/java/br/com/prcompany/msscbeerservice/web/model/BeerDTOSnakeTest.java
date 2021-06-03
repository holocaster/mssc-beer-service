package br.com.prcompany.msscbeerservice.web.model;

import br.com.prcompany.beerevents.model.BeerDTO;
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
        String json = "{\"id\":\"a48cffb9-22ca-4fbd-b0f8-703e746c7620\",\"version\":null,\"created_date\":\"2021-04-08T08:51:39-0300\",\"last_modified_date\":\"2021-04-08T08:51:39-0300\",\"beer_name\":\"Name\",\"beer_style\":\"ALE\",\"upc\":12123123,\"price\":\"12.99\",\"quantity_on_hand\":null}";

        BeerDTO dto = this.objectMapper.readValue(json, BeerDTO.class);

        log.info(dto.toString());
    }
}
