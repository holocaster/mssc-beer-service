package br.com.prcompany.msscbeerservice.services.inventory;

import br.com.prcompany.msscbeerservice.config.FeignClientConfig;
import br.com.prcompany.msscbeerservice.services.inventory.model.BeerInventoryDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;


@FeignClient(name = "${feign_inventory_service_name}", configuration = FeignClientConfig.class)
public interface InventoryServiceFeignClient {

    String INVENTORY_CIRCUIT_BREAKER = "InventoryCircuitBreaker";

    @RequestMapping(method = RequestMethod.GET, value = "${inventory_path}")
    @CircuitBreaker(name = INVENTORY_CIRCUIT_BREAKER, fallbackMethod = "fallbackGetOnHandInventory")
    ResponseEntity<List<BeerInventoryDTO>> getOnHandInventory(@PathVariable UUID beerId);

    default ResponseEntity<List<BeerInventoryDTO>> fallbackGetOnHandInventory(Throwable e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }
}
