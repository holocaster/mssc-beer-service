package br.com.prcompany.msscbeerservice.services.inventory;

import br.com.prcompany.msscbeerservice.services.inventory.model.BeerInventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;


@FeignClient(name = "${feign_inventory_service_name}", fallback = InventoryServiceFeignClientImpl.class)
public interface InventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "${inventory_path}")
    ResponseEntity<List<BeerInventoryDTO>> getOnHandInventory(@PathVariable UUID beerId);
}
