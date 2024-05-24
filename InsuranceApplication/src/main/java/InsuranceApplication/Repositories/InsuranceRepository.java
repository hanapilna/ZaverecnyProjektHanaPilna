package InsuranceApplication.Repositories;

import InsuranceApplication.Models.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Class represents an interface for InsuranceRepository CRUD methods
 * Interface extends the JpaRepository
 */
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

    /**
     * Method returns list of Insurance instances for given User
     * @param id
     * @return List of Insurance
     */
    public List<Insurance> findByUserId(int id);
}
