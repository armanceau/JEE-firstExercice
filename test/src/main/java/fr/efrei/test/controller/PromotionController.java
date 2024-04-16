package fr.efrei.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import fr.efrei.test.dto.CreatePromotion;
import fr.efrei.test.dto.UpdatePromotion;
import fr.efrei.test.model.Promotion;
import fr.efrei.test.service.PromotionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService service;

    @Autowired
    public PromotionController(PromotionService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> findAll(){
        return new ResponseEntity<>(service.findAllPromotions(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Promotion> findOneById(@PathVariable String uuid){
        Promotion promotion = service.findPromotionById(uuid);
        if(promotion != null){
            return new ResponseEntity<>(service.findPromotionById(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Promotion> save(@Valid @RequestBody CreatePromotion promotion){
        Promotion createdPromotion = service.create(promotion);
        return new ResponseEntity<>(createdPromotion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable String uuid){
        boolean isDeleted = service.delete(uuid);
        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);       
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> mettreAJourTotalement(@Valid @PathVariable String uuid, @RequestBody UpdatePromotion promotion){
        boolean isUpdated = service.update(uuid, promotion);
        if(isUpdated){
            return new ResponseEntity<>(HttpStatus.OK);       
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<?> mettreAJourPartiellement(@Valid @PathVariable String uuid, @RequestBody UpdatePromotion promotion){
        boolean isUpdated = service.updatePartielle(uuid, promotion);
        if(isUpdated){
            return new ResponseEntity<>(HttpStatus.OK);       
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
