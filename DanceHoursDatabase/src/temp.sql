set schema 'public';

create table DomainEventEntry (
    aggregateIdentifier varchar(255) not null,
    sequenceNumber bigint not null,
    type varchar(255) not null,
    eventIdentifier varchar(255) not null,
    metaData bytea,
    payload bytea not null,
    payloadRevision varchar(255),
    payloadType varchar(255) not null,
    timestamp varchar(255) not null,
    primary key (aggregateIdentifier, sequenceNumber, type)
);

