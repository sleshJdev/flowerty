package by.itechart.flowerty.web.service;
 import by.itechart.flowerty.persistence.model.Goods;
 import by.itechart.flowerty.persistence.model.User;
 import by.itechart.flowerty.persistence.repository.GoodsRepository;
import javax.transaction.Transactional;

 import by.itechart.flowerty.security.service.UserDetailsServiceImpl;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageImpl;
 import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

 import java.util.ArrayList;

/**
 * Created by Катерина on 28.04.2015.
 *
 * Service for manipulating with goods
 */

@Service
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public Page<Goods> getPage(int page, int size) {

        User user = null;
        user = userDetailsService.getCurrentUser();
        if(user == null){
            return new PageImpl<Goods>(new ArrayList<Goods>());
        }
        return goodsRepository.findByCompany(user.getContact().getCompany(), new PageRequest(page, size));
    }

    @Transactional
    public void save(Goods goods) {
	goodsRepository.save(goods);
    }
}
