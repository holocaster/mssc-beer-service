package br.com.prcompany.msscbeerservice.web.controller;

import br.com.prcompany.msscbeerservice.services.BeerService;
import br.com.prcompany.msscbeerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final BeerService beerService;

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
