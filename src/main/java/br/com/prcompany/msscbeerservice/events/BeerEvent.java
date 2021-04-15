package br.com.prcompany.msscbeerservice.events;

import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    private final BeerDTO beerDTO;
}
