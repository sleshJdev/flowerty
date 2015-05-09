package by.itechart.flowerty.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFlower is a Querydsl query type for Flower
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFlower extends EntityPathBase<Flower> {

    private static final long serialVersionUID = 1801254378L;

    public static final QFlower flower = new QFlower("flower");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QFlower(String variable) {
        super(Flower.class, forVariable(variable));
    }

    public QFlower(Path<? extends Flower> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlower(PathMetadata<?> metadata) {
        super(Flower.class, metadata);
    }

}

