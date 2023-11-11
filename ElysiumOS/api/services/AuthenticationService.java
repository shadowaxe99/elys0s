```java
package api.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthenticationService {

    private Map<String, String> userCredentials = new HashMap<>();
    private Map<String, String> sessionTokens = new HashMap<>();

    public String registerUser(String username, String password) {
        if (userCredentials.containsKey(username)) {
            throw new IllegalArgumentException("User already exists.");
        }
        userCredentials.put(username, hashPassword(password));
        return "User registered successfully.";
    }

    public String loginUser(String username, String password) {
        if (!userCredentials.containsKey(username)) {
            throw new IllegalArgumentException("User does not exist.");
        }
        if (userCredentials.get(username).equals(hashPassword(password))) {
            String token = UUID.randomUUID().toString();
            sessionTokens.put(token, username);
            return token;
        } else {
            throw new IllegalArgumentException("Invalid credentials.");
        }
    }

    public boolean validateToken(String token) {
        return sessionTokens.containsKey(token);
    }

    public String getUsernameFromToken(String token) {
        return sessionTokens.get(token);
    }

    public void logoutUser(String token) {
        sessionTokens.remove(token);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder stringBuffer = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuffer.append(String.format("%02x", b));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password.", e);
        }
    }
}
```