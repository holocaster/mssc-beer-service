package br.com.prcompany.msscbeerservice.web.mappers;

import br.com.prcompany.msscbeerservice.domain.Beer;
import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDTO beerToBeerDTO(Beer beer);

    Beer beerDTOToBeer(BeerDTO beerDTO);
}
