package br.com.prcompany.msscbeerservice.services.brewing;

import br.com.prcompany.msscbeerservice.config.JmsConfig;
import br.com.prcompany.msscbeerservice.domain.Beer;
import br.com.prcompany.msscbeerservice.events.BrewBeerEvent;
import br.com.prcompany.msscbeerservice.repositories.BeerRepository;
import br.com.prcompany.msscbeerservice.services.inventory.BeerInventoryService;
import br.com.prcompany.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        final List<Beer> beers = this.beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQoh = this.beerInventoryService.getOnhandInventory(beer.getId());
            log.debug("Min Qoh : " + beer.getMinOnHand());
            log.debug("Inventory is : " + invQoh);

            if (beer.getMinOnHand() >= invQoh) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(this.beerMapper.beerToBeerDTO(beer)));
            }
        });
    }
}
