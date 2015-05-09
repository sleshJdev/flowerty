package by.itechart.flowerty.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QRight is a Querydsl query type for Right
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRight extends EntityPathBase<Right> {

    private static final long serialVersionUID = -208004947L;

    public static final QRight right = new QRight("right");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<Right.RIGHT_TYPE> name = createEnum("name", Right.RIGHT_TYPE.class);

    public QRight(String variable) {
        super(Right.class, forVariable(variable));
    }

    public QRight(Path<? extends Right> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRight(PathMetadata<?> metadata) {
        super(Right.class, metadata);
    }

}

