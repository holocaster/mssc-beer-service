package br.com.prcompany.msscbeerservice.events;

import br.com.prcompany.msscbeerservice.web.model.BeerDTO;

public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
