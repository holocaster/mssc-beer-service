package br.com.prcompany.msscbeerservice.events;

import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InventoryEvent extends BeerEvent {
    public InventoryEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
