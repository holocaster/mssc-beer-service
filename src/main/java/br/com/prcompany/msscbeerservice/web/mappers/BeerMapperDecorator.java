package br.com.prcompany.msscbeerservice.web.mappers;

import br.com.prcompany.beerevents.model.BeerDTO;
import br.com.prcompany.msscbeerservice.domain.Beer;
import br.com.prcompany.msscbeerservice.services.inventory.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {

    private BeerInventoryService beerInventoryService;
    private BeerMapper beerMapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDTO beerToBeerDTO(Beer beer) {
        return this.beerMapper.beerToBeerDTO(beer);
    }

    @Override
    public br.com.prcompany.beerevents.model.BeerDTO beerToEventBeerDTO(Beer beer) {
        return this.beerMapper.beerToEventBeerDTO(beer);
    }

    @Override
    public BeerDTO beerToBeerDTOWithInventoryOnHand(Beer beer) {
        BeerDTO beerDTO = this.beerMapper.beerToBeerDTO(beer);
        beerDTO.setQuantityOnHand(this.beerInventoryService.getOnhandInventory(beer.getId()));
        return beerDTO;
    }

    @Override
    public Beer beerDTOToBeer(BeerDTO beerDTO) {
        return this.beerMapper.beerDTOToBeer(beerDTO);
    }
}
