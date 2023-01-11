package com.Ecorp.solution.DAO;

import com.Ecorp.solution.model.Promocode;
import com.Ecorp.solution.repository.PromocodeRepository;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromocodeDAO {

    PromocodeRepository promocodeRepository;

    public PromocodeDAO(PromocodeRepository promocodeRepository) {
        this.promocodeRepository = promocodeRepository;
    }

    public List<Promocode> getAllPromocodes() {
        return promocodeRepository.findAll();
    }

    public void delete(Long id) {
        promocodeRepository.deleteById(id);
    }

    public void update(Promocode e) {
        promocodeRepository.update(e.getId(), e.getName(), e.getAmountoff());
    }

    public void create(Promocode p) {
        promocodeRepository.save(p);
    }
}
