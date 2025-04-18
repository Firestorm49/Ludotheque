package fr.eni.ludotheque.controller;

import fr.eni.ludotheque.bll.LocationService;
import fr.eni.ludotheque.classes.Facture;
import fr.eni.ludotheque.classes.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/from-barcode")
    public ResponseEntity<Location> createLocationFromBarcode(@RequestBody String codeBarre) {
        Location loc = locationService.createLocationFromBarcode(codeBarre);
        return new ResponseEntity<>(loc, HttpStatus.CREATED);
    }

    @PostMapping("/return")
    public ResponseEntity<Facture> returnLocations(@RequestBody List<Integer> locationIds) {
        Facture f = locationService.returnLocations(locationIds);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }
}