package br.com.prcompany.msscbeerservice.web.mappers;

import br.com.prcompany.beerevents.model.BeerDTO;
import br.com.prcompany.msscbeerservice.domain.Beer;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDTO beerToBeerDTO(Beer beer);

    br.com.prcompany.beerevents.model.BeerDTO beerToEventBeerDTO(Beer beer);

    BeerDTO beerToBeerDTOWithInventoryOnHand(Beer beer);

    Beer beerDTOToBeer(BeerDTO beerDTO);
}
