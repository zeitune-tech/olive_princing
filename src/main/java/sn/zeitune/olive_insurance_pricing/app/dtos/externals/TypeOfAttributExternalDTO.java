package sn.zeitune.olive_insurance_pricing.app.dtos.externals;

import lombok.Builder;

import java.util.List;

@Builder
public record TypeOfAttributExternalDTO(
        String type, // TEXT, NUMERIC, ENUMERATED, REFERENCE

        // Pour les types numériques
        Boolean isFloating, // true pour les nombres flottants, false pour les entiers
        Integer decimalPlaces, // nombre de décimales pour les flottants

        // Pour les énumérations
        List<AttributOptionExternalDTO> options, // Pour ENUMERATED

        // Pour les références à des tables
        String nomTable, // nom de la table de référence
        String colonneKey, // colonne clé
        String colonneValue // colonne valeur
) {}