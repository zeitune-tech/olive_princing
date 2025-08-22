package sn.zeitune.olive_insurance_pricing.app.dtos.externals;

import lombok.Builder;

import java.util.List;

@Builder
public record ValidationAttributExternalDTO(
        Object valeurMin,
        Object valeurMax,
        Integer longueurMin,
        Integer longueurMax,
        String regex,
        List<String> valeursAutorizees,
        Boolean requis
) {}