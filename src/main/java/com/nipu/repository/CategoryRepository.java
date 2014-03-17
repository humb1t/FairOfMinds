package com.nipu.repository;

import com.nipu.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by humb1t on 10.03.14.
 */
public interface CategoryRepository extends CrudRepository<Category, Long>
{
    
}
