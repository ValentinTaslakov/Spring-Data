package services;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AccountRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private AccountRepository accountRepository;

//    @Override
//    public void register(Student student) {
//        studentRepository.save(student);
//    }
//
//    @Override
//    public void expel(Student student) {
//        studentRepository.delete(student);
//    }

    @Override
    public void register(User user) {

    }

    @Override
    public void expel(User user) {

    }

    @Override
    public void expel(int id) {

    }

    @Override
    public User findStudent(int id) {
        return null;
    }
}

