package com.alsultanseafood.fishsupplychainmanagement.procurement.service;



import com.alsultanseafood.fishsupplychainmanagement.procurement.entity.Procurement;

import java.util.List;





public interface ProcurementService {

    Procurement saveProcurement(
            Procurement procurement);

    List<Procurement> getAllProcurements();

    Procurement getProcurementById(
            Long id);

    Procurement updateProcurement(
            Long id,
            Procurement procurement);

    void deleteProcurement(
            Long id);
}
