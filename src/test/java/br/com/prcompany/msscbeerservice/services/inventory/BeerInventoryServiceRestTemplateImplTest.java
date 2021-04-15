package br.com.prcompany.msscbeerservice.services.inventory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnhandInventory() {
        //Integer qoh = this.beerInventoryService.getOnhandInventory(UUID.fromString(BeerUtils.BEER_1_UUID));

        //System.out.println(qoh);
    }
}