package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.enums.CharacteristicScope;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FormulaRepository extends JpaRepository<Formula, Long>, JpaSpecificationExecutor<Formula> {

    /**
     * Récupère une caractéristique via son UUID.
     */
    @Query("SELECT f FROM formula f WHERE f.uuid = :uuid")
    Optional<Formula> findByUuid(UUID uuid);

}
