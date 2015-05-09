package by.itechart.flowerty.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -210510369L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final NumberPath<Double> cost = createNumber("cost", Double.class);

    public final QContact customer;

    public final QUser delivery;

    public final DatePath<java.util.Date> deliveryDate = createDate("deliveryDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<Item, QItem> items = this.<Item, QItem>createSet("items", Item.class, QItem.class, PathInits.DIRECT2);

    public final QUser manager;

    public final QContact receiver;

    public final QUser staff;

    public final QState state;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrder(PathMetadata<?> metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QContact(forProperty("customer"), inits.get("customer")) : null;
        this.delivery = inits.isInitialized("delivery") ? new QUser(forProperty("delivery"), inits.get("delivery")) : null;
        this.manager = inits.isInitialized("manager") ? new QUser(forProperty("manager"), inits.get("manager")) : null;
        this.receiver = inits.isInitialized("receiver") ? new QContact(forProperty("receiver"), inits.get("receiver")) : null;
        this.staff = inits.isInitialized("staff") ? new QUser(forProperty("staff"), inits.get("staff")) : null;
        this.state = inits.isInitialized("state") ? new QState(forProperty("state")) : null;
    }

}

