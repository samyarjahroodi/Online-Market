package base.baseService.baseServiceImpl;

import base.baseRepository.BaseEntityRepository;
import base.baseService.BaseServiceEntity;
import base.domain.BaseEntity;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class
BaseEntityServiceImpl<ID extends Serializable,
        TYPE extends BaseEntity<ID>, R extends BaseEntityRepository<ID, TYPE>>
        implements BaseServiceEntity<ID, TYPE> {
    protected R repository;

    public BaseEntityServiceImpl(R repository) {
        this.repository = repository;
    }


}
