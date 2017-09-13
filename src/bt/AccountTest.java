package bt;


import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class AccountTest {
	String validCard = "1234567890123478";
	String invalidCard = "1234123412341234";

	@Test
	public void test_creditCardWithAllZeroes(){
		String ccard = "0000000000000000";
		assertFalse(Account.validateCreditCardNumber(ccard));
	}
	
	@Test
	public void test_validCreditCard(){
		String ccard = "1234567890123478";
		assertTrue(Account.validateCreditCardNumber(ccard));		
	}
	
	@Test
	public void test_invalidCreditCard(){
		String ccard = "1234123412341234";
		assertFalse(Account.validateCreditCardNumber(ccard));
	}
	
	@Test
	public void test_ChargeMoreThanBalanceForValidCard(){				
		Account acc = new Account("Tom", validCard, 100l);
		acc.charge(150l);
		assertEquals(acc.getBalance(), 0l);		
	}
	
	@Test
	public void test_NegativeChargeValidCard(){
		Account acc = new Account("Tom", validCard, 100l);
		acc.charge(-50l);
		assertEquals(acc.getBalance(),  0l);
	}
	
	@Test
	public void test_ChargeInValidCard(){
		Account acc = new Account("Tom", invalidCard, 100l);
		acc.charge(50l);
		assertEquals(acc.getBalance(),  0l);
	}
	
	@Test
	public void test_CreditInValidCard(){
		Account acc = new Account("Tom", invalidCard, 100l);
		acc.credit(20l);
		assertEquals(acc.getBalance(),  0l);
	}
	
	@Test
	public void test_NegativeCreditValidCard(){
		Account acc = new Account("Tom", validCard, 100l);
		acc.credit(-50l);
		assertEquals(acc.getBalance(),  0l);
	}
	
	@Test
	public void test_CreditWhenBalanceIsZeroCard(){
		Account acc = new Account("Tom", validCard, 100l);
		acc.credit(20l);
		assertTrue(acc.getBalance() < 0);
	}
	


}
