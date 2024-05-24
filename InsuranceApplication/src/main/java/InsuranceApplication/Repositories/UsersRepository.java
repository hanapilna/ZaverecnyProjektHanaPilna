package InsuranceApplication.Repositories;

import InsuranceApplication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class represents an interface for UsersRepository CRUD methods
 * Interface extends the JpaRepository
 */
public interface UsersRepository extends JpaRepository<User, Integer> {
}
