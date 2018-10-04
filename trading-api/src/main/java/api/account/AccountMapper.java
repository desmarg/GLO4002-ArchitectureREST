package api.account;

import domain.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import api.response.AccountResponse;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings({
            @Mapping(source = "accountNumber.id", target = "accountNumber"),
            @Mapping(source = "credits.amount", target = "credits")
    })
    AccountResponse accountToGetAccountDto(Account account);
}
