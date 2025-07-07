package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.Condition;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
