package com.aksigorta.account.service;

import com.aksigorta.account.model.User;

public interface UserService {
	void save(User user);

	User findByUsername(String username);

}
