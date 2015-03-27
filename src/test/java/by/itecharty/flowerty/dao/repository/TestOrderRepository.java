package by.itecharty.flowerty.dao.repository;

import by.itechart.flowerty.model.Flower;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 27.03.15
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
<<<<<<< HEAD:src/test/java/by/itecharty/flowerty/dao/repository/TestOrderRepository.java
public class TestOrderRepository {
=======
@Repository
public interface FlowerDao {
    public void saveFlower (Flower flower);
    public void deleteFlower (Flower flower);
    public List<Flower> getAllFlowers();

>>>>>>> 804361e4cbbb781065a4f76c633f699035a1edf3:src/main/java/by/itechart/flowerty/dao/FlowerDao.java
}
