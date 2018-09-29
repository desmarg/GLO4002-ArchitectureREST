package api.account;

import domain.Account;
import domain.Credits;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);


    @Mappings({
            @Mapping(source = "accountNumber.id", target = "accountNumber"),
            @Mapping(source = "investorId", target = "investorId"),
            @Mapping(source = "investorProfile", target = "investorProfile"),
            @Mapping(source = "credits.amount", target = "credits")
    })
    GetAccountDto accountToGetAccountDto(Account account);

    default Account postAccountDtoToAccount(PostAccountDto postAccountDto, Long accountNumber) {
        Long investorId = postAccountDto.getInvestorId();
        Credits credits = Credits.fromFloat(postAccountDto.getCredits());
        String investorName = postAccountDto.getInvestorName();
        String email = postAccountDto.getEmail();

        return new Account(investorId, investorName, email, credits, accountNumber);
    }
}
