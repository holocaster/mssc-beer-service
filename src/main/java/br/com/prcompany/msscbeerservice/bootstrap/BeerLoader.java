package br.com.prcompany.msscbeerservice.bootstrap;


import br.com.prcompany.msscbeerservice.domain.Beer;
import br.com.prcompany.msscbeerservice.repositories.BeerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        this.loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (this.beerRepository.count() == 0) {
            this.beerRepository.save(Beer.builder().beerName("Mango Beer")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .upc(32165154L)
                    .price(new BigDecimal("12.95"))
                    .minOnHand(12).build());

            this.beerRepository.save(Beer.builder().beerName("Galaxy Cat")
                    .beerStyle("Pale Ale")
                    .quantityToBrew(200)
                    .upc(32165155L)
                    .price(new BigDecimal("11.95"))
                    .minOnHand(12).build());
        }

        log.info("Loaded Beers: {}", this.beerRepository.count());
    }
}