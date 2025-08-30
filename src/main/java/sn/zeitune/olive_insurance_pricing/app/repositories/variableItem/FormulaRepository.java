package sn.zeitune.olive_insurance_pricing.app.repositories.variableItem;

import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Formula;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FormulaRepository extends GenericRepository<Formula> {

    List<Formula> findAllByPricingTypeAndCoverageAndDeletedIsFalseAndDateEffectiveLessThanEqualOrderByDateEffectiveDescUpdatedAtDesc(
            PricingType pricingType,
            UUID coverage,
            LocalDate now
    );

}
