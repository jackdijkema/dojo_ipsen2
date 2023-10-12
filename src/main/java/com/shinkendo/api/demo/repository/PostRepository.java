package com.shinkendo.api.demo.repository;

import com.shinkendo.api.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
