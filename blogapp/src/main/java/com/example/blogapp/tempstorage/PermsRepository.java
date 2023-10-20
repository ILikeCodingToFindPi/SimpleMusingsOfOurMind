package com.example.blogapp.tempstorage;

import com.example.blogapp.models.Perms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermsRepository extends JpaRepository<Perms, String> {}

/*
This program defines a Spring Data JPA repository interface, PermsRepository, for the Perms entity. It enables database operations (such as insert, update, and retrieval) for the Perms model class and is annotated with @Repository to indicate that it's a Spring-managed repository. The PermsRepository allows interacting with and managing permissions data in a Java application. */