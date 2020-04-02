package de.novatec.bpm.service;

import de.novatec.bpm.model.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean userExists(String id) {
        return !id.equals("bugs.bunny");
    }

    public UserAccount getUserById(String id) throws IllegalArgumentException {
        if (!userExists(id)) {
            throw new IllegalArgumentException("User unknown");
        } else {
            return new UserAccount(id, id + "@novatec-gmbh.de", "DE18199266876354876522", "VOBADEXO887");
        }
    }
}
