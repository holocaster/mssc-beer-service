package br.com.prcompany.msscbeerservice.services.order;

import br.com.prcompany.beerevents.model.BeerOrderDTO;
import br.com.prcompany.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public boolean validateOrder(BeerOrderDTO beerOrderDTO) {
        AtomicInteger beersNotFound = new AtomicInteger();

        beerOrderDTO.getBeerOrderLines().forEach(order -> {
            if (this.beerRepository.findBeerByUpc(order.getUpc()) == null) {
                beersNotFound.incrementAndGet();
            }
        });

        return beersNotFound.get() == 0;
    }
}
