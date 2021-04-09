package br.com.prcompany.msscbeerservice.services.inventory;

import br.com.prcompany.msscbeerservice.utils.BeerUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnhandInventory() {
        Integer qoh = this.beerInventoryService.getOnhandInventory(UUID.fromString(BeerUtils.BEER_1_UUID));

        System.out.println(qoh);
    }
}