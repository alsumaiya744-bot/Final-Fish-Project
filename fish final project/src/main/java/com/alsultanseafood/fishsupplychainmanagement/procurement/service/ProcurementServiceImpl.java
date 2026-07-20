package com.alsultanseafood.fishsupplychainmanagement.procurement.service;




import com.alsultanseafood.fishsupplychainmanagement.procurement.entity.Procurement;
import com.alsultanseafood.fishsupplychainmanagement.Fish.repository.FishRepository;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.entity.Activity;
import com.alsultanseafood.fishsupplychainmanagement.dashboard.repository.ActivityRepository;
import com.alsultanseafood.fishsupplychainmanagement.Fish.entity.Fish;
import com.alsultanseafood.fishsupplychainmanagement.procurement.repository.ProcurementRepository;
import com.alsultanseafood.fishsupplychainmanagement.procurement.service.ProcurementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;



@Service
@Transactional
public class ProcurementServiceImpl
        implements ProcurementService {

    private final ProcurementRepository procurementRepository;
    private final FishRepository fishRepository;
    private final ActivityRepository activityRepository;

    public ProcurementServiceImpl(
            ProcurementRepository procurementRepository,
            FishRepository fishRepository,ActivityRepository activityRepository) {

        this.procurementRepository =
                procurementRepository;

        this.fishRepository =
                fishRepository;

                 this.activityRepository =
            activityRepository;
    }

    @Override
    public Procurement saveProcurement(
            Procurement procurement) {

        Long fishId =
                procurement.getFish()
                        .getFishId();

        Fish fish =
                fishRepository.findById(fishId)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Fish Not Found"));

        fish.setAvailableStock(
                fish.getAvailableStock()
                +
                procurement.getPurchaseQuantity()
        );

        fish.setPurchasePrice(
        procurement.getPurchasePrice()
);

if (fish.getSellingPrice() == null) {
    fish.setSellingPrice(
            procurement.getPurchasePrice()
    );
}

        fishRepository.save(fish);

        procurement.setFish(fish);

        Procurement savedProcurement =
        procurementRepository.save(procurement);

activityRepository.save(
        new Activity(
                "Procurement Added",
                LocalDateTime.now()
        )
);

return savedProcurement;
    }

    @Override
    public List<Procurement>
    getAllProcurements() {

        return procurementRepository.findAll();
    }

    @Override
    public Procurement
    getProcurementById(Long id) {

        return procurementRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new RuntimeException(
                                        "Procurement Not Found"));
    }

    @Override
    public Procurement updateProcurement(
            Long id,
            Procurement procurement) {

        Procurement db =
                getProcurementById(id);

        Fish fish =
                db.getFish();

        Double oldQty =
                db.getPurchaseQuantity();

        Double newQty =
                procurement.getPurchaseQuantity();

        fish.setAvailableStock(
                fish.getAvailableStock()
                -
                oldQty
                +
                newQty
        );

        fishRepository.save(fish);

        db.setSupplierName(
                procurement.getSupplierName());

        db.setSupplierPhone(
                procurement.getSupplierPhone());

        db.setPurchaseQuantity(
                newQty);

        db.setTotalAmount(
                procurement.getTotalAmount());

        db.setPaymentStatus(
                procurement.getPaymentStatus());

        return procurementRepository.save(db);
    }

    @Override
    public void deleteProcurement(
            Long id) {

        Procurement procurement =
                getProcurementById(id);

        Fish fish =
                procurement.getFish();

        fish.setAvailableStock(
                fish.getAvailableStock()
                -
                procurement.getPurchaseQuantity()
        );

        fishRepository.save(fish);

        procurementRepository.delete(procurement);
    }
}