package InsuranceApplication.Models;

import InsuranceApplication.Repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class represents service for the User and implements CRUD methods from the UserRepository interface
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UsersRepository repository;

    /**
     * Method lists all recorded instances of User
     * @return List of Users
     */
    public List<User> listAll(){
        return repository.findAll();
    }

    /**
     * Method saves a new User
     * @param user
     */
    public void save(User user){
        repository.save(user);
    }

    /**
     * Method returns User based on its id
     * @param id
     * @return user
     */
    public User get(int id){
        return repository.findById(id).get();
    }

    /**
     * Method deletes User based on its id
     * @param id
     */
    public void delete(int id){
        repository.deleteById(id);
    }

}
