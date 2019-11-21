package com.only4play.jdbc.support;

import static org.springframework.util.CollectionUtils.isEmpty;
import com.google.common.base.Preconditions;
import com.only4play.common.exception.ValidationException;
import com.only4play.common.model.ValidateResult;
import com.only4play.common.validator.CreateGroup;
import com.only4play.common.validator.UpdateGroup;
import com.only4play.common.validator.ValidateGroup;
import io.vavr.control.Try;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: Gim
 * @Date: 2019/11/18 16:28
 * @Description:
 */
@Slf4j
public abstract class BaseService {

    public <T, ID> Updater<T, ID> doUpdate(CrudRepository<T, ID> repository) {
        return new Updater(repository);
    }

    public <T, ID> Creator<T, ID> doCreate(CrudRepository<T, ID> repository){
        return new Creator<>(repository);
    }

    static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public class Updater<T, ID> {

        private final CrudRepository<T, ID> repository;
        private T entity;
        private Consumer<T> successCallback = t -> log.info(t.toString());
        private Consumer<? super Throwable> errorCallback = e -> log.error(e.getMessage());

        private Updater(CrudRepository<T, ID> repository) {
            this.repository = repository;
        }

        public Updater<T, ID> loadById(ID id) {
            Preconditions.checkArgument(Objects.nonNull(id),"id is null");
            Optional<T> loadEntity = repository.findById(id);
            this.entity = loadEntity.orElse(entity);
            return this;
        }

        public Updater<T, ID> update(Consumer<T> consumer) {
            Preconditions.checkArgument(Objects.nonNull(entity),"entity is null");
            consumer.accept(this.entity);
            return this;
        }

        public Updater<T, ID> sucCallback(Consumer<T> consumer){
            this.successCallback = consumer;
            return this;
        }

        public Updater<T, ID> errorCallback(Consumer<? super Throwable> consumer){
            this.errorCallback = consumer;
            return this;

        }

        public Optional<T> execute() {
            doValidate(this.entity,UpdateGroup.class);
            T save = Try.of(() -> repository.save(entity))
                .onSuccess(successCallback)
                .onFailure(errorCallback).getOrNull();
            return Optional.ofNullable(save);
        }
    }

    public class Creator<T, ID>{

        private final CrudRepository<T, ID> repository;
        private T t;
        private Consumer<T> successCallback = t -> log.info(t.toString());
        private Consumer<? super Throwable> errorCallback = e -> e.printStackTrace();

        public Creator(CrudRepository<T, ID> repository) {
            this.repository = repository;
        }

        public Creator<T, ID> create(Supplier<T> supplier){
            this.t = supplier.get();
            return this;
        }
        public Creator<T, ID> update(Consumer<T> t){
            Preconditions.checkArgument(Objects.nonNull(t),"entity must supply");
            t.accept(this.t);
            return this;
        }

        public Creator<T, ID> sucCallback(Consumer<T> consumer){
            this.successCallback = consumer;
            return this;
        }

        public Creator<T, ID> errorCallback(Consumer<? super Throwable> consumer){
            this.errorCallback = consumer;
            return this;

        }

        public Optional<T> execute(){
            doValidate(this.t,CreateGroup.class);
            T save = Try.of(() -> repository.save(t))
                .onSuccess(successCallback)
                .onFailure(errorCallback).getOrNull();
            return Optional.ofNullable(save);
        }
    }

    private <T> void doValidate(T t, Class<? extends ValidateGroup> group){
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t, group);
        if (!isEmpty(constraintViolations)) {
            List<ValidateResult> results = constraintViolations.stream()
                .map(cv -> new ValidateResult(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());
            throw new ValidationException(results);
        }
    }

}