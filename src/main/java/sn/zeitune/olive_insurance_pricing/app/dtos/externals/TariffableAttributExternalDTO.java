package sn.zeitune.olive_insurance_pricing.app.dtos.externals;

import lombok.Builder;

@Builder
public record TariffableAttributExternalDTO(
        String nom,
        String libelle,
        String description,
        String entite, // POLICE, RISK, CARACTERISTIQUE
        String chemin, // ex: "police.primeNette", "risk.marque", "caracteristique.uuid_xxx"
        TypeOfAttributExternalDTO type,
        Boolean obligatoire,
        String groupeAffichage,
        Integer ordreAffichage,
        ValidationAttributExternalDTO validation
) {
}