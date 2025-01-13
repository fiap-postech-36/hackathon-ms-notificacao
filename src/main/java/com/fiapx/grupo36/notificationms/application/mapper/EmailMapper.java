package com.fiapx.grupo36.notificationms.application.mapper;

import com.fiapx.grupo36.notificationms.application.gates.EmailInput;
import com.fiapx.grupo36.notificationms.domain.core.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmailMapper {
    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);


    Email emailInputToEmail(final EmailInput emailInput);
}
