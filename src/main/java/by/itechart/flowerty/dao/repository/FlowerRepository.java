package by.itechart.flowerty.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.itechart.flowerty.model.Flower;

public interface FlowerRepository extends CrudRepository<Flower, Long> {
}
