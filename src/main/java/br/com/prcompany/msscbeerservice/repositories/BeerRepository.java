package br.com.prcompany.msscbeerservice.repositories;

import br.com.prcompany.beerevents.model.BeerStyleEnum;
import br.com.prcompany.msscbeerservice.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest);

    Page<Beer> findAllByBeerName(String beerName, PageRequest pageRequest);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyleEnum, PageRequest pageRequest);

    Optional<Beer> findBeerByUpc(String upc);
}
