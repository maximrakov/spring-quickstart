package spring_prac.dao;

import org.springframework.stereotype.Component;
import spring_prac.models.User;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonService {
    List<User>users = new ArrayList<>();

    public List<User> getAll(){
        return users;
    }

    public User getUser(int id){
        return users.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(User user){
        users.add(user);
    }
}
