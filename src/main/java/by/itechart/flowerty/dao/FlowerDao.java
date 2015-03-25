package by.itechart.flowerty.dao;

import by.itechart.flowerty.model.Flower;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 22.03.15
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface FlowerDao {
    public void saveFlower (Flower flower);
    public void deleteFlower (Flower flower);
    public List<Flower> getAllFlowers();

}
