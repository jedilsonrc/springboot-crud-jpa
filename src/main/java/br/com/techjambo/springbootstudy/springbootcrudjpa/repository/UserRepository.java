package br.com.techjambo.springbootstudy.springbootcrudjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techjambo.springbootstudy.springbootcrudjpa.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{


}
