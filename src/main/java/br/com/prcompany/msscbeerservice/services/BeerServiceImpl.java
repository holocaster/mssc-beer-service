package br.com.prcompany.msscbeerservice.services;

import br.com.prcompany.msscbeerservice.domain.Beer;
import br.com.prcompany.msscbeerservice.repositories.BeerRepository;
import br.com.prcompany.msscbeerservice.services.exceptions.ObjectNotFoundException;
import br.com.prcompany.msscbeerservice.web.mappers.BeerMapper;
import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDTO getById(UUID beerId) {
        return this.beerMapper.beerToBeerDTO(
                this.beerRepository.findById(beerId).orElseThrow(ObjectNotFoundException::new));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        Beer beer = this.beerMapper.beerDTOToBeer(beerDTO);
        return this.beerMapper.beerToBeerDTO(this.beerRepository.save(beer));
    }

    @Override
    public BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO) {
        Beer beer = this.beerRepository.findById(beerId).orElseThrow(ObjectNotFoundException::new);

        beer.setBeerName(beerDTO.getBeerName());
        beer.setBeerStyle(beerDTO.getBeerStyle().name());
        beer.setPrice(beerDTO.getPrice());
        beer.setUpc(beerDTO.getUpc());

        return this.beerMapper.beerToBeerDTO(this.beerRepository.save(beer));
    }
}
