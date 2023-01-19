package ru.netology.springboot_2.repository;

import org.springframework.stereotype.Repository;
import ru.netology.springboot_2.model.Authorities;

import java.util.*;

@Repository
public class UserRepository {
    Map<String, String>  users = new HashMap<>();
    Map<String, ArrayList<Authorities>> acls = new HashMap<>();

    UserRepository() {
        this.addUser("admin", "1111", new Authorities[]{Authorities.DELETE, Authorities.WRITE, Authorities.READ});
        this.addUser("user", "2222", new Authorities[]{Authorities.WRITE, Authorities.READ});
    }

    public List<Authorities> getUserAuthorities(String user, String password) {

        if ((users.get(user) != null) && users.get(user).equals(password)) {
            if (acls.containsKey(user)) {
                return acls.get(user);
            }
        }

        return new ArrayList<>();
    }

    private void addUser(String user, String password, Authorities[] acl) {
        users.put(user, password);

        ArrayList<Authorities> tempAcls = new ArrayList<Authorities>();
        for (int i = acl.length - 1; i >= 0; i--) {
            tempAcls.add(acl[i]);
        }
        acls.put(user, tempAcls);
    }
}