package by.itechart.flowerty.web.service;
<<<<<<< HEAD
 import by.itechart.flowerty.persistence.repository.GoodsRepository;
import by.itechart.flowerty.persistence.model.Goods;
=======

import javax.transaction.Transactional;
import by.itechart.flowerty.persistence.repository.GoodsRepository;
import by.itechart.flowerty.persistence.repository.model.Goods;
>>>>>>> 3309bdb9718ea8c9c8e946478fc6c564cc990f2d
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by Катерина on 28.04.2015.
 *
 * Service for manipulating with goods
 */

@Service
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    public Page<Goods> getPage(int page, int size) {
	return goodsRepository.findAll(new PageRequest(page, size));
    }

    @Transactional
    public void save(Goods goods) {
	goodsRepository.save(goods);
    }
}
