package br.com.prcompany.msscbeerservice.web.controller;

import br.com.prcompany.msscbeerservice.services.BeerService;
import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import br.com.prcompany.msscbeerservice.web.model.BeerPagedList;
import br.com.prcompany.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyleEnum) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = this.beerService.listBeers(beerName, beerStyleEnum, PageRequest.of(pageNumber, pageSize));

        return ResponseEntity.ok(beerList);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") final UUID beerId) {
        final BeerDTO beerDTO = this.beerService.getById(beerId);
        return new ResponseEntity<>(beerDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDTO beerDTO) {
        final BeerDTO saveDTO = this.beerService.saveNewBeer(beerDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveDTO.getId().toString()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@Valid @RequestBody BeerDTO beerDTO, @PathVariable("beerId") final UUID beerId) {
        this.beerService.updateBeer(beerId, beerDTO);
        return ResponseEntity.noContent().build();
    }
}
