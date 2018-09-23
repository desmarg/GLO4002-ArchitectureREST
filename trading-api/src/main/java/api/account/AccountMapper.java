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
            @Mapping(source = "investorId", target = "investorId"),
            @Mapping(source = "investorName", target = "investorName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "credits", target = "credits")
    })
    Account accountCreatorDTOToAccount(AccountCreatorDTO accountCreatorDTO);

    @Mappings({
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "investorId", target = "investorId"),
            @Mapping(source = "investorProfile", target = "investorProfile"),
            @Mapping(source = "credits", target = "credits")
    })
    AccountInformationDTO accountToAccountInformationDTO(Account account);
}
