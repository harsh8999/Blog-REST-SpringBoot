package com.personal.blogappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.blogappapi.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
    
}
