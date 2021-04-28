package br.com.prcompany.msscbeerservice.services.inventory;

import br.com.prcompany.msscbeerservice.services.inventory.model.BeerInventoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerInventoryServiceFeign implements BeerInventoryService {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getOnhandInventory(UUID beerId) {

        log.debug("Calling inventory service - beerID: {}", beerId);

        final ResponseEntity<List<BeerInventoryDTO>> responseEntity = this.inventoryServiceFeignClient.getOnHandInventory(beerId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody()).stream()
                .mapToInt(BeerInventoryDTO::getQuantityOnHand).sum();

        log.debug("BeerId: {} - On hand is: {}", beerId, onHand);
        return onHand;
    }
}
