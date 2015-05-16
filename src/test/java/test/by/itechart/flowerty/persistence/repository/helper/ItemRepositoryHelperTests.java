package test.by.itechart.flowerty.persistence.repository.helper;

import by.itechart.flowerty.persistence.model.Item;

/**
 * Created by Rostislav on 16-May-15
 */

public abstract class ItemRepositoryHelperTests {

    public static Item getItemWithIdOne() {
        Item item = new Item();

        item.setId(1L);
        item.setQuantity(1);
        item.setGoods(GoodsRepositoryHelperTests.getGoodsWithIdOne());
        item.setOrder(OrderRepositoryHelperTests.getOrderWithIdOne());

        return item;
    }
}
