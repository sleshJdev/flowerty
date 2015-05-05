package by.itechart.flowerty.persistence.repository;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.config.aware.JpaConfigurationAware;
import by.itechart.flowerty.persistence.model.Goods;
import by.itechart.flowerty.persistence.model.Item;



/**
@author Мария 30.03.15
 */
public class TestItemRepository extends JpaConfigurationAware {
    @Autowired
    private ItemRepository itemRepository;

    @Ignore
    @Test
    public void findItem_ValidId_ReturnsItem() {
        Item item = itemRepository.findOne(1l);
        Assert.assertEquals(item.getQuantity(), 10);
        Assert.assertEquals(item.getGoods().getFlower().getName(), "Yellow Tulip");
    }

    @Ignore
    @Test
    public void findItem_InValidId_ReturnsNull() {
        Item item = itemRepository.findOne(1000l);
        Assert.assertNull(item);
    }
    @Ignore
    @Test
    public void saveItem_ValidItem_ReturnsSameItem() {
        Goods goods = new Goods();
        goods.setId(1l);
        Item item = new Item();
        item.setGoods(goods);
        item.setQuantity(10);
        item = itemRepository.save(item);
        Assert.assertEquals(10, item.getQuantity());
        Assert.assertEquals(goods, item.getGoods());
    }
}
