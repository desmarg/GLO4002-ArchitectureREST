package models;

import java.math.BigDecimal;

public class Account {
    private Long investorId;
    private String investorName;
    private String email;
    private BigDecimal credits;
    private InvestorProfile investorProfile;
    private static Long accountNumber = 1L;

	public Account() {
		this.accountNumber++;
	}

	public Long getAccountNumber() {
		return this.accountNumber;
	}

	public void setInvestorName(String newName) {
		this.investorName = newName;
	}
}
