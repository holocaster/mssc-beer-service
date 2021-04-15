package br.com.prcompany.msscbeerservice.events;

import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {

    private BeerDTO beerDTO;
}
