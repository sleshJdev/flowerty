package by.itechart.flowerty.service;

import by.itechart.flowerty.model.Flower;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 22.03.15
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
@Component
public interface FlowerService {
    public void saveFlower (Flower flower);
    public void addQuantity(Integer add);
    public Integer getRemain();


}
