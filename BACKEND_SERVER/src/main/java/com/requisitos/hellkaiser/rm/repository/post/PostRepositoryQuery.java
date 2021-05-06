package com.requisitos.hellkaiser.rm.repository.post;

import com.requisitos.hellkaiser.rm.repository.filter.PostFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryQuery{
    PageImpl filtrar(PostFilter filter, Pageable pageable);
}
