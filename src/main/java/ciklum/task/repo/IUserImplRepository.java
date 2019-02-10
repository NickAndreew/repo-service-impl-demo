package ciklum.task.repo;

import ciklum.task.impl.IUserImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserImplRepository extends JpaRepository<IUserImpl, String> {
}
