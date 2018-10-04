package api.account;

import domain.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings({
            @Mapping(source = "accountNumber.id", target = "accountNumber"),
            @Mapping(source = "credits.amount", target = "credits")
    })
    AccountGetDto accountToGetAccountDto(Account account);
}
