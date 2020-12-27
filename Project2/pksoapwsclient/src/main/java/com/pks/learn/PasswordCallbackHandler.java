package com.pks.learn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

/**
 * @author soni_
 * 
 *         Provide password for given userid whatever comes in the request
 *
 */
public class PasswordCallbackHandler implements CallbackHandler {

	Map<String, String> passwords = new HashMap<>();

	public PasswordCallbackHandler() {
		passwords.put("myuser", "mypassword");
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (Callback callback : callbacks) {

			WSPasswordCallback pc = (WSPasswordCallback) callback;
			String password = passwords.get(pc.getIdentifier());
			// Set password back to callback
			pc.setPassword(password);
		}

	}

}
