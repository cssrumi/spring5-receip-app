package com.cssrumi.demo.repositories;

import com.cssrumi.demo.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
