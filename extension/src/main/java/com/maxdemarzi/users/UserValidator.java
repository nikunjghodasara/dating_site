package com.maxdemarzi.users;

import com.maxdemarzi.Exceptions;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

import static com.maxdemarzi.schema.Properties.EMAIL;
import static com.maxdemarzi.schema.Properties.NAME;
import static com.maxdemarzi.schema.Properties.PASSWORD;
import static com.maxdemarzi.schema.Properties.USERNAME;


public class UserValidator {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String usernamePattern = "^[a-z0-9_]{3,32}";

    public static HashMap validate(String body) throws IOException {
        HashMap<String, Object> input;

        if ( body == null) {
            throw Exceptions.invalidInput;
        }

        // Parse the input
        try {
            input = objectMapper.readValue(body, HashMap.class);
        } catch (Exception e) {
            throw Exceptions.invalidInput;
        }

        if (!input.containsKey(USERNAME)) {
            throw UserExceptions.missingUsernameParameter;
        } else {
            String username = (String)input.get(USERNAME);
            if (username.equals("")) {
                throw UserExceptions.emptyUsernameParameter;
            } else if (!username.matches(usernamePattern)) {
                throw UserExceptions.invalidUsernameParameter;
            }
        }

        if (!input.containsKey(EMAIL)) {
            throw UserExceptions.missingEmailParameter;
        } else {
            String email = (String)input.get(EMAIL);
            if (email.equals("")) {
                throw UserExceptions.emptyEmailParameter;
            } else if (!email.contains("@")) {
                throw UserExceptions.invalidEmailParameter;
            }
        }

        if (!input.containsKey(NAME)) {
            throw UserExceptions.missingNameParameter;
        } else {
            String email = (String) input.get(NAME);
            if (email.equals("")) {
                throw UserExceptions.emptyNameParameter;
            }
        }

        if (!input.containsKey(PASSWORD)) {
            throw UserExceptions.missingPasswordParameter;
        } else {
            String email = (String) input.get(PASSWORD);
            if (email.equals("")) {
                throw UserExceptions.emptyPasswordParameter;
            }
        }

        input.put(USERNAME, ((String) input.get(USERNAME)).toLowerCase());

        return input;
    }
}