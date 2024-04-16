package fr.efrei.test.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.efrei.test.dto.CreatePromotion;
import fr.efrei.test.dto.UpdatePromotion;
import fr.efrei.test.model.Promotion;
import fr.efrei.test.repository.PromotionRepository;
import jakarta.transaction.Transactional;

@Service
public class PromotionService {
    private final PromotionRepository repository;

    @Autowired
    public PromotionService(PromotionRepository repository){
        this.repository = repository;
    }

    public Promotion findPromotionById(String uuid){
        System.out.println("je suis appelé (trouve une seule promotion)");
        return repository.findOneByUuid(uuid).orElse(null);
    }

    public List<Promotion> findAllPromotions(){
        System.out.println("je suis appelé (trouve toutes les promotions)");
        return repository.findAllByDeletedAtNull();
    }

    public Promotion create(CreatePromotion promotion){
        System.out.println("je suis appelé (création étudiant)");
        Promotion promotionACreer = new Promotion(promotion.getName(), promotion.getStudents());
        return repository.save(promotionACreer);
    }

    @Transactional
    public boolean delete(String uuid){
        Promotion promotionASupprimer = findPromotionById(uuid);
        if(promotionASupprimer != null && promotionASupprimer.getDeletedAt() != null){
            promotionASupprimer.setDeletedAt(LocalDateTime.now());
            repository.save(promotionASupprimer);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean update(String uuid, UpdatePromotion promotion){
        Promotion promotionAModifier = findPromotionById(uuid);
        if(promotionAModifier != null){
            promotionAModifier.setStudents(promotion.getStudents());
            promotionAModifier.setName(promotion.getName());   
            repository.save(promotionAModifier);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean updatePartielle(String uuid, UpdatePromotion promotion){
        Promotion promotionAModifier = findPromotionById(uuid);
        if(promotionAModifier != null){
            if(!promotion.getStudents().isEmpty()){
                promotionAModifier.setStudents(promotion.getStudents());
            }
            if(!promotion.getName().isEmpty()){
                promotionAModifier.setName(promotion.getName());
            }
            return true;
        }
        return false;
    }
}
