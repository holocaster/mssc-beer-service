package br.com.prcompany.msscbeerservice.services.brewing;

import br.com.prcompany.beerevents.events.BrewBeerEvent;
import br.com.prcompany.beerevents.events.NewInventoryEvent;
import br.com.prcompany.beerevents.model.BeerDTO;
import br.com.prcompany.beerevents.utils.EventsConstants;
import br.com.prcompany.msscbeerservice.domain.Beer;
import br.com.prcompany.msscbeerservice.repositories.BeerRepository;
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
    @JmsListener(destination = EventsConstants.BREWING_REQUEST_QUEUE)
    public void listen(@Payload BrewBeerEvent event) {
        BeerDTO beerDTO = event.getBeerDTO();

        Beer beer = this.beerRepository.getOne(beerDTO.getId());

        beerDTO.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent inventoryEvent = new NewInventoryEvent(beerDTO);

        log.debug("Brewed beer: " + beer.getMinOnHand() + " - Qoh:" + beerDTO.getQuantityOnHand());

        this.jmsTemplate.convertAndSend(EventsConstants.NEW_INVENTORY_QUEUE, inventoryEvent);

    }
}
