package sn.zeitune.olive_insurance_pricing.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.GroupedFieldValueDto;
import sn.zeitune.olive_insurance_pricing.app.services.GroupedFieldValueService;

import java.util.List;

@RestController
@RequestMapping("/app/grouped-field-values")
@RequiredArgsConstructor
@Slf4j
public class GroupedFieldValueController {

    private final GroupedFieldValueService groupedFieldValueService;

    @PostMapping
    public ResponseEntity<GroupedFieldValueDto> create(@Valid @RequestBody GroupedFieldValueDto groupedFieldValueDto) {
        log.info("REST request to create grouped field value: {}", groupedFieldValueDto.getName());
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupedFieldValueDto> getById(@PathVariable Long id) {
        log.info("REST request to get grouped field value by ID: {}", id);
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<GroupedFieldValueDto>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all grouped field values with pagination");
        return null;
    }

    @GetMapping("/by-group/{groupName}")
    public ResponseEntity<List<GroupedFieldValueDto>> getByGroupName(@PathVariable String groupName) {
        log.info("REST request to get grouped field values by group name: {}", groupName);
        return null;
    }

    @GetMapping("/search-group")
    public ResponseEntity<List<GroupedFieldValueDto>> searchByGroupName(@RequestParam String groupName) {
        log.info("REST request to search grouped field values by group name: {}", groupName);
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<List<GroupedFieldValueDto>> searchByName(@RequestParam String name) {
        log.info("REST request to search grouped field values by name: {}", name);
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupedFieldValueDto> update(@PathVariable Long id, @Valid @RequestBody GroupedFieldValueDto groupedFieldValueDto) {
        log.info("REST request to update grouped field value with ID: {}", id);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete grouped field value with ID: {}", id);
        return null;
    }
}
