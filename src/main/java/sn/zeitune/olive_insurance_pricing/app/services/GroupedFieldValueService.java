package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.GroupedFieldValueDto;

import java.util.List;
import java.util.UUID;

public interface GroupedFieldValueService {

    GroupedFieldValueDto create(GroupedFieldValueDto groupedFieldValueDto);

    GroupedFieldValueDto findById(Long id);

    GroupedFieldValueDto findByUuid(UUID uuid);

    List<GroupedFieldValueDto> findAll();

    Page<GroupedFieldValueDto> findAll(Pageable pageable);

    List<GroupedFieldValueDto> findByGroupName(String groupName);

    List<GroupedFieldValueDto> searchByGroupName(String groupName);

    List<GroupedFieldValueDto> searchByName(String name);

    GroupedFieldValueDto update(Long id, GroupedFieldValueDto groupedFieldValueDto);

    GroupedFieldValueDto updateByUuid(UUID uuid, GroupedFieldValueDto groupedFieldValueDto);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}