package br.com.prcompany.msscbeerservice.events;

import br.com.prcompany.msscbeerservice.web.model.BeerDTO;

public class InventoryEvent extends BeerEvent {
    public InventoryEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
