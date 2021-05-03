package br.com.prcompany.msscbeerservice.services.inventory;

import br.com.prcompany.msscbeerservice.services.inventory.model.BeerInventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "inventory-failover")
public interface InventoryFailoverFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "${inventory_failover_path}")
    ResponseEntity<List<BeerInventoryDTO>> getOnHandInventory();
}
