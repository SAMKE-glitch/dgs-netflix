package com.netflix.lolomodemo.datafetchers;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.test.EnableDgsTest;
import com.netflix.lolomodemo.ArtworkService;
import com.netflix.lolomodemo.ShowsRepository;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {LolomoDatafetcher.class, ShowsRepository.class, ArtworkService.class})
@EnableDgsTest
class LolomoDatafetcherTest {
    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Test
    void search() {
        @Language("GraphQL")
        var query = """
                query {
                    search(searchFilter: {title: "the"}) {
                    title
                    }
                }
                """;

        List<String> titles =  dgsQueryExecutor.executeAndExtractJsonPath(query, "data.search[*].title");

        assertThat(titles).containsExactly("The Witcher", "The Last Dance");
    }
}