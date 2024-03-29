package fr.efrei.test.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import fr.efrei.test.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, String> {
    
}
