package com.sena.lunches.service;

import com.sena.lunches.entities.Benefit;
import java.util.List;

public interface BenefitService {

    public List<Benefit> getBenefit();

    public Benefit saveBenefit(Benefit benefit);

    public Benefit getBenefitById(Integer id);

    public Benefit updateBenefit(Integer id, Benefit benefit);

    public void deleteBenefit(Integer id);


}
