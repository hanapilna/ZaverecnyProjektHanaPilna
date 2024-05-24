package InsuranceApplication.Models;

import InsuranceApplication.Repositories.InsuranceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents service for the Insurance and implements CRUD methods from the InsuranceRepository interface
 */
@Service
@Transactional
public class InsuranceService {
    @Autowired
    InsuranceRepository insuranceRepository;

    /**
     * Method lists all recorded instances of Insurance
     * @return List of Insurance
     */
    public List<Insurance> listAll(){return insuranceRepository.findAll();}

    /**
     * Method saves a new Insurance
     * @param insurance
     */
    public void save(Insurance insurance){insuranceRepository.save(insurance);}

    /**
     * Method returns Insurance based on its id
     * @param id
     * @return Insurance
     */
    public Insurance get(int id){return insuranceRepository.findById(id).get();}

    /**
     * Method deletes Insurance based on its id
     * @param id
     */
    public void delete(int id){insuranceRepository.deleteById(id);}

    /**
     * Method lists all the instances of Insurance for a given User based on the user_id
     * @param id
     * @return List of Insurance for the given User
     */
    public List<Insurance> getAllInsurance(int id){
        List<Insurance> listOfInsurance = new ArrayList<>();
        insuranceRepository.findByUserId(id).forEach(listOfInsurance::add);
        return listOfInsurance;
    }
}
