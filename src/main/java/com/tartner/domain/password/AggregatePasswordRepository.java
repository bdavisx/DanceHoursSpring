package com.tartner.domain.password;

import com.tartner.dancehours.querymodel.jpa.AggregatePasswordsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AggregatePasswordRepository
    extends CrudRepository<AggregatePasswordsEntity, UUID> {
}
