package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Benefit;
import com.sena.lunches.repository.Benefit_repo;
import com.sena.lunches.service.BenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenefitServiceImpl implements BenefitService {

    @Autowired
    private Benefit_repo benefitRepo;

    @Override
    public List<Benefit> getBenefit() {
        return benefitRepo.findAll();
    }

    @Override
    public Benefit saveBenefit(Benefit benefit) {
        return benefitRepo.save(benefit);
    }

    @Override
    public Benefit getBenefitById(Integer id) {
        return benefitRepo.findById(id).orElse(null);
    }

    @Override
    public Benefit updateBenefit(Integer id, Benefit benefit) {
        Benefit oldBenefit = benefitRepo.findById(id).orElse(null);
        if (oldBenefit != null){
            oldBenefit.setNom_benefit(benefit.getNom_benefit());
            oldBenefit.setDescription_benefit(benefit.getDescription_benefit());
            oldBenefit.setDate_start(benefit.getDate_start());
            oldBenefit.setDate_end(benefit.getDate_end());
            return benefitRepo.save(oldBenefit);
        }
        return null;
    }

    @Override
    public void deleteBenefit(Integer id) {
        benefitRepo.deleteById(id);
    }
}
