package by.itechart.flowerty.persistence.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QContact is a Querydsl query type for Contact
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QContact extends EntityPathBase<Contact> {

    private static final long serialVersionUID = 1721637489L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContact contact = new QContact("contact");

    public final QAddress address;

    public final DatePath<java.util.Date> birthday = createDate("birthday", java.util.Date.class);

    public final QCompany company;

    public final StringPath email = createString("email");

    public final StringPath fathername = createString("fathername");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final SetPath<Phone, QPhone> phones = this.<Phone, QPhone>createSet("phones", Phone.class, QPhone.class, PathInits.DIRECT2);

    public final StringPath surname = createString("surname");

    public QContact(String variable) {
        this(Contact.class, forVariable(variable), INITS);
    }

    public QContact(Path<? extends Contact> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContact(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContact(PathMetadata<?> metadata, PathInits inits) {
        this(Contact.class, metadata, inits);
    }

    public QContact(Class<? extends Contact> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
    }

}

