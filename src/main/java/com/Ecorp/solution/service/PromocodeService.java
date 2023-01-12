package com.Ecorp.solution.service;

import com.Ecorp.solution.DAO.OrdersDAO;
import com.Ecorp.solution.DAO.PromocodeDAO;
import com.Ecorp.solution.model.Orders;
import com.Ecorp.solution.model.Promocode;
import com.Ecorp.solution.model.Server;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromocodeService {

    PromocodeDAO promocodeDAO;

    public List<Promocode> getAllPromocodes() {
        return promocodeDAO.getAllPromocodes();
    }

    public void delete(Long id) {
        promocodeDAO.delete(id);
    }

    public void update(Promocode e) {
        promocodeDAO.update(e);
    }

    public void create(Promocode p) {
        promocodeDAO.create(p);
    }

    public double checkCode(String code) {
        List<Promocode> codes = getAllPromocodes();
        for(Promocode promo: codes) {
            if (promo.getName().equals(code)) {
                return promo.getAmountoff();
            }
        }

        return 0;
    }
}
