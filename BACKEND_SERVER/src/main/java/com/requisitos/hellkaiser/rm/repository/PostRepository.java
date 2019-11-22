package com.requisitos.hellkaiser.rm.repository;

import com.requisitos.hellkaiser.rm.model.Post;
import com.requisitos.hellkaiser.rm.repository.post.PostRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PostRepository extends JpaRepository<Post, Serializable>, PostRepositoryQuery {
}
