package com.sole.saas.supplier.cvts;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wjd
 * @description
 * @date 2022-06-06
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SequenceCvt {
    SequenceCvt INSTANCE = Mappers.getMapper(SequenceCvt.class);
}
