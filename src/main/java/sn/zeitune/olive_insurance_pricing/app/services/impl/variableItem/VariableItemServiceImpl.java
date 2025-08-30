package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.VariableItemRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.VariableItemMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.VariableItemRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.VariableItemService;



@Service
@Transactional
public class VariableItemServiceImpl extends RetrieveGenericServiceImpl<VariableItem, VariableItemRequestDTO, VariableItemResponseDTO> implements VariableItemService {

    public VariableItemServiceImpl (VariableItemRepository variableItemRepository) {
        super(variableItemRepository, VariableItemMapper.getInstance());
    }

}