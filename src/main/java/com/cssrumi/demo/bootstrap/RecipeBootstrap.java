package com.cssrumi.demo.bootstrap;

import com.cssrumi.demo.model.*;
import com.cssrumi.demo.repositories.CategoryRepository;
import com.cssrumi.demo.repositories.RecipeRepository;
import com.cssrumi.demo.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("loading Bootstrap Data");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Artykułem tym rozpoczynam cykl związany z szeroko pojętą tematyką budowy systemów" +
                " transakcyjnych w środowisku Java Enterprise. Temu podstawowemu zagadnieniu związanemu bezpośrednio z " +
                "tworzeniem solidnych systemów informatycznych poświęcono do tej pory niewiele książek i artykułów, a" +
                " zdobycie praktycznej wiedzy w tym zakresie jest stosunkowo trudne. Z tego powodu w ramach cyklu w " +
                "kolejnych artykułach postaram się w sposób systematyczny przedstawić następujące zagadnienia:");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Artykułem tym rozpoczynam cykl związany z szeroko pojętą tematyką budowy systemów" +
                " transakcyjnych w środowisku Java Enterprise. Temu podstawowemu zagadnieniu związanemu bezpośrednio z " +
                "tworzeniem solidnych systemów informatycznych poświęcono do tej pory niewiele książek i artykułów, a" +
                " zdobycie praktycznej wiedzy w tym zakresie jest stosunkowo trudne. Z tego powodu w ramach cyklu w " +
                "kolejnych artykułach postaram się w sposób systematyczny przedstawić następujące zagadnienia:");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.getIngredients().add(new Ingredient("1ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("2ripe avocados1", new BigDecimal(2), tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("3ripe avocados2", new BigDecimal(2), teaSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("4ripe avocados3", new BigDecimal(2), cupsUom));
        guacRecipe.getIngredients().add(new Ingredient("5ripe avocados4", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("6ripe avocados5", new BigDecimal(2), pintUom));
        guacRecipe.getIngredients().add(new Ingredient("7ripe avocados6", new BigDecimal(2), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(10);
        tacosRecipe.setPrepTime(10);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1Artykułem tym rozpoczynam cykl związany z szeroko pojętą tematyką budowy systemów" +
                " transakcyjnych w środowisku Java Enterprise. Temu podstawowemu zagadnieniu związanemu bezpośrednio z " +
                "tworzeniem solidnych systemów informatycznych poświęcono do tej pory niewiele książek i artykułów, a" +
                " zdobycie praktycznej wiedzy w tym zakresie jest stosunkowo trudne. Z tego powodu w ramach cyklu w " +
                "kolejnych artykułach postaram się w sposób systematyczny przedstawić następujące zagadnienia:");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("2Artykułem tym rozpoczynam cykl związany z szeroko pojętą tematyką budowy systemów" +
                " transakcyjnych w środowisku Java Enterprise. Temu podstawowemu zagadnieniu związanemu bezpośrednio z " +
                "tworzeniem solidnych systemów informatycznych poświęcono do tej pory niewiele książek i artykułów, a" +
                " zdobycie praktycznej wiedzy w tym zakresie jest stosunkowo trudne. Z tego powodu w ramach cyklu w " +
                "kolejnych artykułach postaram się w sposób systematyczny przedstawić następujące zagadnienia:");

        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.getIngredients().add(new Ingredient("1ripe avocados", new BigDecimal(2), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("2ripe avocados", new BigDecimal(1), dashUom));
        tacosRecipe.getIngredients().add(new Ingredient("3ripe avocados", new BigDecimal(23), tableSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("4ripe avocados", new BigDecimal(22), teaSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("5ripe avocados", new BigDecimal(21), cupsUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;
    }
}
