package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.GroupedFieldValueDto;
import sn.zeitune.olive_insurance_pricing.app.services.GroupedFieldValueService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupedFieldValueServiceImpl implements GroupedFieldValueService {
    @Override
    public GroupedFieldValueDto create(GroupedFieldValueDto groupedFieldValueDto) {
        return null;
    }

    @Override
    public GroupedFieldValueDto findById(Long id) {
        return null;
    }

    @Override
    public GroupedFieldValueDto findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<GroupedFieldValueDto> findAll() {
        return List.of();
    }

    @Override
    public Page<GroupedFieldValueDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<GroupedFieldValueDto> findByGroupName(String groupName) {
        return List.of();
    }

    @Override
    public List<GroupedFieldValueDto> searchByGroupName(String groupName) {
        return List.of();
    }

    @Override
    public List<GroupedFieldValueDto> searchByName(String name) {
        return List.of();
    }

    @Override
    public GroupedFieldValueDto update(Long id, GroupedFieldValueDto groupedFieldValueDto) {
        return null;
    }

    @Override
    public GroupedFieldValueDto updateByUuid(UUID uuid, GroupedFieldValueDto groupedFieldValueDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteByUuid(UUID uuid) {

    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }
}
