package com.capgemini.service;

import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repository;

	// CREATE
	public Map<String, Object> createAccount(Account account) {
		Map<String, Object> response = new HashMap<>();

		if (account.getBalance() == null || account.getBalance() < 0) {
			response.put("message", "Balance cannot be negative");
			return response;
		}

		Account saved = repository.save(account);

		response.put("message", "Account created successfully");
		response.put("id", saved.getId());

		return response;
	}

	// READ ONE
	public Object getAccount(int id) {
		Optional<Account> optional = repository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}

		return "Account not found";
	}

	// READ ALL
	public List<Account> getAllAccounts() {
		return repository.findAll();
	}

	// UPDATE
	public Map<String, String> updateAccount(int id, Account updated) {
		Map<String, String> response = new HashMap<>();

		Optional<Account> optional = repository.findById(id);

		if (optional.isEmpty()) {
			response.put("message", "Account not found");
			return response;
		}

		if (updated.getBalance() == null || updated.getBalance() < 0) {
			response.put("message", "Balance cannot be negative");
			return response;
		}

		Account existing = optional.get();

		existing.setAccountHolderName(updated.getAccountHolderName());
		existing.setBalance(updated.getBalance());

		repository.save(existing);

		response.put("message", "Account updated successfully");
		return response;
	}

	// DELETE
	public Map<String, String> deleteAccount(int id) {
		Map<String, String> response = new HashMap<>();

		if (!repository.existsById(id)) {
			response.put("message", "Account not found");
			return response;
		}

		repository.deleteById(id);

		response.put("message", "Account deleted successfully");
		return response;
	}

	// DEPOSIT
	public Map<String, Object> deposit(int accountId, Double amount) {
		Map<String, Object> response = new HashMap<>();

		Optional<Account> optional = repository.findById(accountId);

		if (optional.isEmpty()) {
			response.put("message", "Account not found");
			return response;
		}

		if (amount == null || amount <= 0) {
			response.put("message", "Invalid amount");
			return response;
		}

		Account account = optional.get();
		account.setBalance(account.getBalance() + amount);

		repository.save(account);

		response.put("message", "Deposit successful");
		response.put("updatedBalance", account.getBalance());

		return response;
	}

	// WITHDRAW
	public Map<String, Object> withdraw(int accountId, Double amount) {
		Map<String, Object> response = new HashMap<>();

		Optional<Account> optional = repository.findById(accountId);

		if (optional.isEmpty()) {
			response.put("message", "Account not found");
			return response;
		}

		if (amount == null || amount <= 0) {
			response.put("message", "Invalid amount");
			return response;
		}

		Account account = optional.get();

		if (account.getBalance() < amount) {
			response.put("message", "Insufficient balance");
			return response;
		}

		account.setBalance(account.getBalance() - amount);

		repository.save(account);

		response.put("message", "Withdrawal successful");
		response.put("updatedBalance", account.getBalance());

		return response;
	}
}