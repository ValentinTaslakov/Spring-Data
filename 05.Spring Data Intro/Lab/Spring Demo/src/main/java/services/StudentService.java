package services;

import models.Account;
import models.User;

import java.util.List;

public interface StudentService {

    void register(User user);

    void expel(User user);

    void expel(int id);

    User findStudent(int id);

//    List<Account> findSampleByMajor(Major major);
}

