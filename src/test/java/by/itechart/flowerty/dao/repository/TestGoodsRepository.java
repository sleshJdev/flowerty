package by.itechart.flowerty.dao.repository;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import by.itechart.flowerty.config.JpaConfigurationAware;
import by.itechart.flowerty.dao.repository.GoodsRepository;
import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Flower;
import by.itechart.flowerty.model.Goods;

/**
 @author Мария 29.03.15
 */
public class TestGoodsRepository extends JpaConfigurationAware {
    @Autowired
    private GoodsRepository goodsRepository;

    @Ignore
    @Test
    public void findGoods_ValidId_ReturnsGoods () {
        Goods goods = goodsRepository.findOne(1l);
        Assert.assertEquals("Yellow Tulip", goods.getFlower().getName());
    }
    @Ignore
    @Test
    public void findGoods_InvalidId_ReturnsNull () {
        Goods goods = goodsRepository.findOne(100l);
        Assert.assertNull(goods);
    }
    @Ignore
    @Test
    public void saveGoods_ValidGoods_ReturnsSameGoods() {
        Flower fl = new Flower();
        fl.setId(4l);
        Company company = new Company();
        company.setId(1l);
        Goods goods = new Goods();
        goods.setCompany(company);
        goods.setFlower(fl);
        goods.setCost(15.7);
        goods.setRemain(111);
        goods = goodsRepository.save(goods);
        Assert.assertEquals((Object) goods.getCost(), 15.7);

    }
    @Ignore
    @Test
    public void findGoods_ValidCompany_ReturnsPageOfGoods() {
        Company company = new Company();
        company.setId(1l);
        Page page = goodsRepository.findByCompany(company, new PageRequest(0, 10));
        Assert.assertNotNull(page);
        Assert.assertNotEquals(0, page.getContent().size());
    }
    @Ignore
    @Test
    public void findGoods_InvalidCompany_ReturnsEmptyPage() {
        Company company = new Company();
        company.setId(1000l);
        Page page = goodsRepository.findByCompany(company, new PageRequest(1, 10));
        Assert.assertNotNull(page);
        Assert.assertEquals(0, page.getNumberOfElements());
    }

}
