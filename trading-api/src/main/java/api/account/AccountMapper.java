package api.account;

import domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings({
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "investorId", target = "investorId"),
            @Mapping(source = "investorProfile", target = "investorProfile"),
            @Mapping(source = "credits", target = "credits")
    })
    AccountInformationDto accountToAccountInformationDto(Account account);
}
