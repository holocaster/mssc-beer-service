package br.com.prcompany.msscbeerservice.web.controller;

import br.com.prcompany.beerevents.model.BeerDTO;
import br.com.prcompany.beerevents.model.BeerPagedList;
import br.com.prcompany.beerevents.model.BeerStyleEnum;
import br.com.prcompany.msscbeerservice.services.BeerService;
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
@RequestMapping("/api/v1/")
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(path = "/beer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyleEnum,
                                                   @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") boolean showInventoryOnHand) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = this.beerService.listBeers(beerName, beerStyleEnum, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return ResponseEntity.ok(beerList);
    }

    @GetMapping("/beerUpc/{upc}")
    public ResponseEntity<BeerDTO> getBeerByUpc(@PathVariable("upc") final String upc,
                                                @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") boolean showInventoryOnHand) {
        final BeerDTO beerDTO = this.beerService.getByUpc(upc, showInventoryOnHand);
        return new ResponseEntity<>(beerDTO, HttpStatus.OK);
    }

    @GetMapping("/beer/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") final UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") boolean showInventoryOnHand) {
        final BeerDTO beerDTO = this.beerService.getById(beerId, showInventoryOnHand);
        return new ResponseEntity<>(beerDTO, HttpStatus.OK);
    }

    @PostMapping("/beer")
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDTO beerDTO) {
        final BeerDTO saveDTO = this.beerService.saveNewBeer(beerDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveDTO.getId().toString()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/beer/{beerId}")
    public ResponseEntity updateBeerById(@Valid @RequestBody BeerDTO beerDTO, @PathVariable("beerId") final UUID beerId) {
        this.beerService.updateBeer(beerId, beerDTO);
        return ResponseEntity.noContent().build();
    }
}
