package br.com.prcompany.msscbeerservice.services;

import br.com.prcompany.beerevents.model.BeerDTO;
import br.com.prcompany.beerevents.model.BeerPagedList;
import br.com.prcompany.beerevents.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDTO getById(UUID beerId, boolean showInventoryOnHand);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest, boolean showInventoryOnHand);

    BeerDTO getByUpc(String upc, boolean showInventoryOnHand);
}
