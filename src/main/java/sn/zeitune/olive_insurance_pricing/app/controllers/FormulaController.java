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
import sn.zeitune.olive_insurance_pricing.app.dtos.FormulaDto;
import sn.zeitune.olive_insurance_pricing.app.services.FormulaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/formulas")
@RequiredArgsConstructor
@Slf4j
public class FormulaController {

    private final FormulaService formulaService;
    
    @PostMapping
    public ResponseEntity<FormulaDto> create(@Valid @RequestBody FormulaDto formulaDto) {
        log.info("REST request to create formula: {}", formulaDto.getLabel());
        FormulaDto createdFormula = formulaService.create(formulaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFormula);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormulaDto> getById(@PathVariable Long id) {
        log.info("REST request to get formula by ID: {}", id);
        FormulaDto formula = formulaService.findById(id);
        return ResponseEntity.ok(formula);
    }

    @GetMapping
    public ResponseEntity<Page<FormulaDto>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all formulas with pagination");
        Page<FormulaDto> formulas = formulaService.findAll(pageable);
        return ResponseEntity.ok(formulas);
    }

    @GetMapping("/by-product/{product}")
    public ResponseEntity<List<FormulaDto>> getByProduct(@PathVariable UUID product) {
        log.info("REST request to get formulas by product: {}", product);
        List<FormulaDto> formulas = formulaService.findByProduct(product);
        return ResponseEntity.ok(formulas);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FormulaDto>> searchByLabel(@RequestParam String label) {
        log.info("REST request to search formulas by label: {}", label);
        List<FormulaDto> formulas = formulaService.searchByLabel(label);
        return ResponseEntity.ok(formulas);
    }

    @GetMapping("/search-expression")
    public ResponseEntity<List<FormulaDto>> searchByExpression(@RequestParam String expression) {
        log.info("REST request to search formulas by expression: {}", expression);
        List<FormulaDto> formulas = formulaService.searchByExpression(expression);
        return ResponseEntity.ok(formulas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormulaDto> update(@PathVariable Long id, @Valid @RequestBody FormulaDto formulaDto) {
        log.info("REST request to update formula with ID: {}", id);
        FormulaDto updatedFormula = formulaService.update(id, formulaDto);
        return ResponseEntity.ok(updatedFormula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete formula with ID: {}", id);
        formulaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
