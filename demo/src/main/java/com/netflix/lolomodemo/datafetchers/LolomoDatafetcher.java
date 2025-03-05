package com.netflix.lolomodemo.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.lolomodemo.codegen.types.ShowCategory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;



@Slf4j
// DgsComponent tells Netflix DGS (GraphQL framework for SB) that this
// class provides data for GraphQL queries
@DgsComponent
public class LolomoDatafetcher {

    /**
     * getting all the shows
     * @return
     */
    @DgsQuery
    public List<ShowCategory> lolomo() {
        /**
         * a method that when a user requests lolomo, this method gets executed
         * @return: Show Categories
         */
        log.info("GET request : getting all Show categories");
        return List.of(
                ShowCategory.newBuilder().id(1).name("Top 10").build(),
                ShowCategory.newBuilder().id(2).name("Continue Watching").build()
        );

    }
}
