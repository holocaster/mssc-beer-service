package br.com.prcompany.msscbeerservice.services.brewing;

import br.com.prcompany.msscbeerservice.config.JmsConfig;
import br.com.prcompany.msscbeerservice.domain.Beer;
import br.com.prcompany.msscbeerservice.events.BrewBeerEvent;
import br.com.prcompany.msscbeerservice.events.InventoryEvent;
import br.com.prcompany.msscbeerservice.repositories.BeerRepository;
import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(@Payload BrewBeerEvent event) {
        BeerDTO beerDTO = event.getBeerDTO();

        Beer beer = this.beerRepository.getOne(beerDTO.getId());

        beerDTO.setQuantityOnHand(beer.getQuantityToBrew());

        InventoryEvent inventoryEvent = new InventoryEvent(beerDTO);

        log.info("Brewed beer: " + beer.getMinOnHand() + " - Qoh:" + beerDTO.getQuantityOnHand());

        this.jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, inventoryEvent);

    }
}
