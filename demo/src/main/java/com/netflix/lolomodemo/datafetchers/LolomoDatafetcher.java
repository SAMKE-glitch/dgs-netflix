package com.netflix.lolomodemo.datafetchers;

import com.netflix.graphql.dgs.*;
import com.netflix.lolomodemo.ArtworkService;
import com.netflix.lolomodemo.ShowsRepository;
import com.netflix.lolomodemo.codegen.types.SearchFilter;
import com.netflix.lolomodemo.codegen.types.Show;
import com.netflix.lolomodemo.codegen.types.ShowCategory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
// DgsComponent tells Netflix DGS (GraphQL framework for SB) that this
// class provides data for GraphQL queries
@DgsComponent
public class LolomoDatafetcher {
    private final ShowsRepository showsRepository;
    private final ArtworkService artworkService;

    public LolomoDatafetcher(ShowsRepository showsRepository, ArtworkService artworkService) {
        this.showsRepository = showsRepository;
        this.artworkService = artworkService;
    }

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
                ShowCategory.newBuilder().id(1).name("Top 10").shows(showsRepository.showsForCategory(1)).build(),
                ShowCategory.newBuilder().id(2).name("Continue Watching").shows(showsRepository.showsForCategory(2)).build()
        );

    }

    @DgsData(parentType = "Show")
    public String artworkUrl(DgsDataFetchingEnvironment dge) {
        Show show = dge.getSourceOrThrow();
        return artworkService.generateForTitle(show.getTitle());
    }

    @DgsQuery
    public List<Show> search(@InputArgument SearchFilter searchFilter) {
        return showsRepository.allShows().stream().filter(s -> s.getTitle().toLowerCase().startsWith(searchFilter.getTitle())).toList();
    }
}
