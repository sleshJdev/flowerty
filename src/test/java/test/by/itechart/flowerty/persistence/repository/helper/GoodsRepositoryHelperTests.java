package test.by.itechart.flowerty.persistence.repository.helper;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Flower;
import by.itechart.flowerty.persistence.model.Goods;

/**
 * Created by Rostislav on 16-May-15
 */

public abstract class GoodsRepositoryHelperTests {

    public static Company getCompany() {
        Company company = new Company();

        company.setId(1L);
        company.setName("FandJ");
        company.setWebsite("www.FandJ.com");

        return company;
    }

    public static Flower getFlower() {
        Flower flower = new Flower();

        flower.setId(1L);
        flower.setName("Red Rose");

        return flower;
    }

    public static Goods getGoodsWithIdOne() {
        Goods goods = new Goods();

        goods.setId(1L);
        goods.setCost(12.12);
        goods.setRemain(11);
        goods.setCompany(getCompany());
        goods.setFlower(getFlower());

        return goods;
    }
}
