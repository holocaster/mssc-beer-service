package br.com.prcompany.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

@Slf4j
@JsonTest
class BeerDTOTest extends BaseTest {

    @Test
    void testSerializeDTO() throws JsonProcessingException {
        BeerDTO beerDTO = super.getDTO();

        String jsonString = this.objectMapper.writeValueAsString(beerDTO);

        log.info(jsonString);
    }

    @Test
    void testDeserialize() throws JsonProcessingException {
        String json = "{\"id\":\"aa4a2e51-21e3-4e5b-a98d-09980bb4963c\",\"version\":null,\"createdDate\":\"2021-04-08T08:51:08-0300\",\"lastModifiedDate\":\"2021-04-08T08:51:08-0300\",\"beerName\":\"Name\",\"beerStyle\":\"ALE\",\"upc\":12123123,\"price\":\"12.99\",\"quantityOnHand\":null}";

        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        BeerDTO dto = this.objectMapper.readValue(json, BeerDTO.class);

        log.info(dto.toString());
    }

}