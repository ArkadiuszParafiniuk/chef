package com.bazzarek.dudle.chef.repository.data;

import static com.bazzarek.dudle.chef.model.TypeOfDish.BREAKFAST;
import static com.bazzarek.dudle.chef.model.TypeOfDish.DESSERT;
import static com.bazzarek.dudle.chef.model.TypeOfDish.DINNER;

import com.bazzarek.dudle.chef.model.Ingredient;
import com.bazzarek.dudle.chef.model.RecipeTag;
import java.util.List;
import java.util.UUID;
import com.bazzarek.dudle.chef.model.Recipe;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@ChangeLog
@Component
@Slf4j
@Profile({"dev"})
public class RecipeChangeLog {

  @ChangeSet(id = "putInitialRecipe", order = "001", author = "bazzarek", runAlways = true)
  public void putInitialRecipe(MongoDatabase mongoDatabase,
                                              MongockTemplate mongockTemplate) {
    mongockTemplate.remove(new Query(), "recipe");
    mongockTemplate.remove(new Query(), "recipeTag");

    mongockTemplate.save(Recipe.builder()
            .uuid(UUID.randomUUID().toString())
            .title("test breakfast")
            .content("breakfast content")
            .tags(List.of("TEST", "SŁODKIE"))
            .ingredients(List.of(
                Ingredient.builder().ingredient("cukier").amount("100 g").build(),
                Ingredient.builder().ingredient("sól").amount("10 g").build(),
                Ingredient.builder().ingredient("woda").amount("2 wiadra").build()))
            .typeOfDish(BREAKFAST)
            .build());

    mongockTemplate.save(Recipe.builder()
        .uuid(UUID.randomUUID().toString())
        .title("test dinner")
        .content("dinner content")
        .tags(List.of("TEST", "SŁONE"))
        .ingredients(List.of(
            Ingredient.builder().ingredient("cukier").amount("100 g").build(),
            Ingredient.builder().ingredient("sól").amount("10 g").build(),
            Ingredient.builder().ingredient("woda").amount("2 wiadra").build()))
        .typeOfDish(DINNER)
        .build());

    mongockTemplate.save(Recipe.builder()
        .uuid(UUID.randomUUID().toString())
        .title("test DESSERT")
        .content("dessert content")
        .tags(List.of("TEST", "SŁODKIE"))
        .ingredients(List.of(
            Ingredient.builder().ingredient("cukier").amount("100 g").build(),
            Ingredient.builder().ingredient("sól").amount("10 g").build(),
            Ingredient.builder().ingredient("woda").amount("2 wiadra").build()))
        .typeOfDish(DESSERT)
        .build());

    mongockTemplate.save(RecipeTag.builder().tag("TEST").build());
    mongockTemplate.save(RecipeTag.builder().tag("SŁODKIE").build());
    mongockTemplate.save(RecipeTag.builder().tag("SŁONE").build());
  }
}
