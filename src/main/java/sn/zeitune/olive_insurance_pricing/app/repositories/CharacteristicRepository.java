package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import sn.zeitune.olive_insurance_pricing.app.entities.Characteristic;
import sn.zeitune.olive_insurance_pricing.enums.CharacteristicScope;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long>, JpaSpecificationExecutor<Characteristic> {

    /**
     * Récupère une caractéristique via son UUID.
     */
    @Query("SELECT c FROM caracteristique c WHERE c.uuid = :uuid")
    Optional<Characteristic> findByUuid(UUID uuid);

    /**
     * Liste toutes les caractéristiques par scope.
     */
    @Query("SELECT c FROM caracteristique c WHERE c.scope = :scope")
    List<Characteristic> findAllByScope(CharacteristicScope scope);
}
