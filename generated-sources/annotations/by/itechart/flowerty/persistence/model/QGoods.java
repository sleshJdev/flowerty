package by.itechart.flowerty.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGoods is a Querydsl query type for Goods
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGoods extends EntityPathBase<Goods> {

    private static final long serialVersionUID = -217977369L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGoods goods = new QGoods("goods");

    public final QCompany company;

    public final NumberPath<Double> cost = createNumber("cost", Double.class);

    public final QFlower flower;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final NumberPath<Integer> remain = createNumber("remain", Integer.class);

    public QGoods(String variable) {
        this(Goods.class, forVariable(variable), INITS);
    }

    public QGoods(Path<? extends Goods> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGoods(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGoods(PathMetadata<?> metadata, PathInits inits) {
        this(Goods.class, metadata, inits);
    }

    public QGoods(Class<? extends Goods> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
        this.flower = inits.isInitialized("flower") ? new QFlower(forProperty("flower")) : null;
    }

}

