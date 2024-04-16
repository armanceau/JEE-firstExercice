package fr.efrei.test.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.efrei.test.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, String> {
    List<Promotion> findAllByDeletedAtNull();
    Optional<Promotion> findOneByUuid(String uuid);
    void deleteByUuid(String uuid);

    @SuppressWarnings("unchecked")
    Promotion save(Promotion promotion);
}
