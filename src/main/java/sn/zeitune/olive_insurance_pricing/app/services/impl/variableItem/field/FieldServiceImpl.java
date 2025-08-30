package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.field;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.FieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.Field;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field.FieldMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.field.FieldRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.field.FieldService;
import sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.RetrieveGenericServiceImpl;


@Service
@Transactional
public class FieldServiceImpl extends RetrieveGenericServiceImpl<Field, FieldRequestDTO, FieldResponseDTO> implements FieldService {

    public FieldServiceImpl(FieldRepository fieldRepository) {
        super(fieldRepository, FieldMapper.getInstance());
    }

}
