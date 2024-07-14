package com.example.project_spring_boot_backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project_spring_boot_backend.domain.Contact;

import java.util.Optional;

@Repository
public interface Repo extends JpaRepository<Contact, String> {
    Optional<Contact> findById(String id);
}
