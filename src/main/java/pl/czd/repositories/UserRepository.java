package pl.czd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czd.entities.User;

/**
 * Created by robeek on 01.06.17.
 */

public interface UserRepository extends JpaRepository<User, Long>
{
    public User findById(String id);
}
