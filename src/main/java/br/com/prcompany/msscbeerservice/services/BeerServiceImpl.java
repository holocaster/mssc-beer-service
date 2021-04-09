package br.com.prcompany.msscbeerservice.services;

import br.com.prcompany.msscbeerservice.domain.Beer;
import br.com.prcompany.msscbeerservice.repositories.BeerRepository;
import br.com.prcompany.msscbeerservice.services.exceptions.ObjectNotFoundException;
import br.com.prcompany.msscbeerservice.utils.StringUtils;
import br.com.prcompany.msscbeerservice.web.mappers.BeerMapper;
import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import br.com.prcompany.msscbeerservice.web.model.BeerPagedList;
import br.com.prcompany.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !Objects.isNull(beerStyleEnum)) {
            //search for both
            beerPage = this.beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyleEnum, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && Objects.isNull(beerStyleEnum)) {
            // search for beerName
            beerPage = this.beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !Objects.isNull(beerStyleEnum)) {
            // search for beerStyleEnum
            beerPage = this.beerRepository.findAllByBeerStyle(beerStyleEnum, pageRequest);
        } else {
            beerPage = this.beerRepository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(beerPage.getContent().stream().map(this.beerMapper::beerToBeerDTO).collect(Collectors.toList()),
                PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPagedList;
    }
}
