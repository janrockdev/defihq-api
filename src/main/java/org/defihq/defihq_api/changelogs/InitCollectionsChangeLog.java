package org.defihq.defihq_api.changelogs;

import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;


@ChangeUnit(id = "init-collections", order = "001", author = "bootify")
public class InitCollectionsChangeLog {

    @BeforeExecution
    public void beforeExecution(final MongoTemplate mongoTemplate) {
        mongoTemplate.createCollection("event", CollectionOptions.empty()
                .validator(Validator.schema(MongoJsonSchema.builder()
                .required("category", "event")
                .properties(
                        JsonSchemaProperty.date("timestamp"),
                        JsonSchemaProperty.string("userName"),
                        JsonSchemaProperty.string("category"),
                        JsonSchemaProperty.string("event"),
                        JsonSchemaProperty.string("result")).build())));
        mongoTemplate.createCollection("user", CollectionOptions.empty()
                .validator(Validator.schema(MongoJsonSchema.builder()
                .required("userPassword", "created")
                .properties(
                        JsonSchemaProperty.string("userName"),
                        JsonSchemaProperty.string("userPassword"),
                        JsonSchemaProperty.date("created")).build())));
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution(final MongoTemplate mongoTemplate) {
    }

    @Execution
    public void execution(final MongoTemplate mongoTemplate) {
    }

    @RollbackExecution
    public void rollbackExecution(final MongoTemplate mongoTemplate) {
    }

}
