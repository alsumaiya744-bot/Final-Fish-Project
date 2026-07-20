package com.alsultanseafood.fishsupplychainmanagement.procurement.controller;








import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;
import com.alsultanseafood.fishsupplychainmanagement.procurement.entity.Procurement;


import com.alsultanseafood.fishsupplychainmanagement.procurement.service.ProcurementService;


import org.springframework.web.bind.annotation.*;

import java.util.List;





@RestController
@RequestMapping("/api/procurements")

public class ProcurementController {

    private final ProcurementService
            procurementService;

    public ProcurementController(
            ProcurementService procurementService) {

        this.procurementService =
                procurementService;
    }

    @PostMapping
    public Procurement save(
            @RequestBody
            Procurement procurement) {

        return procurementService
                .saveProcurement(procurement);
    }

    @GetMapping
    public List<Procurement> getAll() {

        return procurementService
                .getAllProcurements();
    }

    @GetMapping("/{id}")
    public Procurement getById(
            @PathVariable Long id) {

        return procurementService
                .getProcurementById(id);
    }

    @PutMapping("/{id}")
    public Procurement update(
            @PathVariable Long id,
            @RequestBody Procurement procurement) {

        return procurementService
                .updateProcurement(
                        id,
                        procurement);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        procurementService
                .deleteProcurement(id);
    }

   
}