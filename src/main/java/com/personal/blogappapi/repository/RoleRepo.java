package com.personal.blogappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.blogappapi.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
    
}
