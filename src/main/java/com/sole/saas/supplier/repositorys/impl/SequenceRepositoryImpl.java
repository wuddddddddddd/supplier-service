package com.sole.saas.supplier.repositorys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sole.saas.supplier.mappers.SequenceMapper;
import com.sole.saas.supplier.models.po.Sequence;
import com.sole.saas.supplier.repositorys.SequenceRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wjd
 * @description
 * @date 2022-06-06
 */
@Repository
public class SequenceRepositoryImpl extends ServiceImpl<SequenceMapper, Sequence> implements SequenceRepository {

    private final SequenceMapper sequenceMapper;

    public SequenceRepositoryImpl(SequenceMapper sequenceMapper) {
        this.sequenceMapper = sequenceMapper;
    }
}
