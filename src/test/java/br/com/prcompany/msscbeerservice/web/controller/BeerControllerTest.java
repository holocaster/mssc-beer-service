package br.com.prcompany.msscbeerservice.web.controller;

import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import br.com.prcompany.msscbeerservice.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.UUID;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    private static final String BASE_URL = "/api/v1/beer/";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDTO getValidBeerDTO() {
        return BeerDTO.builder().beerName("My Beer").beerStyle(BeerStyleEnum.ALE).price(BigDecimal.ONE).upc(123123L).build();
    }

    @Test
    void getBeerById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDTO beerDTO = this.getValidBeerDTO();
        String beerDTOJson = this.objectMapper.writeValueAsString(beerDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(beerDTOJson)).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDTO beerDTO = this.getValidBeerDTO();
        String beerDTOJson = this.objectMapper.writeValueAsString(beerDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON).content(beerDTOJson)).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}