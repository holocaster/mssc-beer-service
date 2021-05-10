package br.com.prcompany.msscbeerservice.services.inventory;

import br.com.prcompany.msscbeerservice.services.inventory.model.BeerInventoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
//@Service
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

    private RestTemplate restTemplate;

    @Value("${inventory_path}")
    private String inventoryPath;

    @Value("${beer_inventory_service_host}")
    private String beerInventoryServiceHost;


    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder, @Value("${inventory_user}") String inventoryUser,
                                                @Value("${inventory_password}") String inventoryPassword) {
        this.restTemplate = restTemplateBuilder
                .basicAuthentication(inventoryUser, inventoryPassword)
                .build();
    }

    @Override
    public Integer getOnhandInventory(UUID uuid) {

        log.debug("Calling Inventory Service");

        ResponseEntity<List<BeerInventoryDTO>> responseEntity = this.restTemplate.exchange(this.beerInventoryServiceHost + this.inventoryPath, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<BeerInventoryDTO>>() {
                }, uuid);

        return Objects.requireNonNull(responseEntity.getBody()).stream().mapToInt(BeerInventoryDTO::getQuantityOnHand).sum();
    }
}
