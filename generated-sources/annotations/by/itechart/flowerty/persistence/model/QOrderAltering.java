package by.itechart.flowerty.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrderAltering is a Querydsl query type for OrderAltering
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrderAltering extends EntityPathBase<OrderAltering> {

    private static final long serialVersionUID = -1998075125L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderAltering orderAltering = new QOrderAltering("orderAltering");

    public final StringPath comment = createString("comment");

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOrder order;

    public final QState state;

    public final QUser user;

    public QOrderAltering(String variable) {
        this(OrderAltering.class, forVariable(variable), INITS);
    }

    public QOrderAltering(Path<? extends OrderAltering> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderAltering(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderAltering(PathMetadata<?> metadata, PathInits inits) {
        this(OrderAltering.class, metadata, inits);
    }

    public QOrderAltering(Class<? extends OrderAltering> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
        this.state = inits.isInitialized("state") ? new QState(forProperty("state")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

