package br.com.prcompany.msscbeerservice.services;

import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import br.com.prcompany.msscbeerservice.web.model.BeerPagedList;
import br.com.prcompany.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDTO getById(UUID beerId);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest);
}
