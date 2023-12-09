package it.gniado.empik.repository;

import it.gniado.empik.entity.GithubLogin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<GithubLogin, String> {}
