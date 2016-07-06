package de.hsos.kbse.messaging.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-06T17:40:01")
@StaticMetamodel(MyMessage.class)
public class MyMessage_ { 

    public static volatile SingularAttribute<MyMessage, String> author;
    public static volatile SingularAttribute<MyMessage, Long> id;
    public static volatile SingularAttribute<MyMessage, String> dateSent;
    public static volatile SingularAttribute<MyMessage, String> message;

}