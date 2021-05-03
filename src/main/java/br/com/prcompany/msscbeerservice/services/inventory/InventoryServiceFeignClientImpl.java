package br.com.prcompany.msscbeerservice.services.inventory;

import br.com.prcompany.msscbeerservice.services.inventory.model.BeerInventoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientImpl implements InventoryServiceFeignClient {

    private final InventoryFailoverFeignClient inventoryFailoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDTO>> getOnHandInventory(UUID beerId) {
        return this.inventoryFailoverFeignClient.getOnHandInventory();
    }
}
