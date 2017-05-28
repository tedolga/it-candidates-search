package com.roi.poc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.roi.poc.dto.PageRequest;
import com.roi.poc.dto.SearchResult;
import com.roi.poc.dto.domain.Candidate;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class SearchService {
    private static final String CLIENT_PROPERTIES_FILE_NAME = "elastic.client.settings.properties";
    private static final ClassLoader CLASS_LOADER = ClassLoader.getSystemClassLoader();

    private TransportClient elasticClient;
    @Value("${elastic.index.name}")
    private String indexName;
    @Value("${elastic.host.name}")
    private String hostName;
    @Value("${elastic.port}")
    private int port;

    public SearchResult getSearchResults(String query, PageRequest pageRequest) {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(query,
                "work_experience.position", "work_experience.technologies");
        multiMatchQueryBuilder.type(MultiMatchQueryBuilder.Type.CROSS_FIELDS);
        SearchResponse response = elasticClient.prepareSearch(indexName)
                .setTypes("candidates")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.nestedQuery("work_experience", multiMatchQueryBuilder, ScoreMode.Avg))
                .setSize(pageRequest.getPageSize())
                .setFrom((pageRequest.getPageNumber() - 1) * pageRequest.getPageSize())
                .get();
        return getSearchResult(pageRequest, response);
    }

    private SearchResult getSearchResult(PageRequest pageRequest, SearchResponse response) {
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits();
        List<Candidate> result = new ArrayList<Candidate>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (SearchHit hit : hits.getHits()) {
            String docSource = new String(hit.source());
            try {
                Candidate candidate = objectMapper.readValue(docSource, Candidate.class);
                result.add(candidate);
            } catch (IOException e) {
                throw new IllegalStateException(String.format("Cannot parse returned document: %s", docSource),e);
            }
        }

        return new SearchResult(total, pageRequest.getPageNumber(), pageRequest.getPageSize(), result);
    }

    @PostConstruct
    public void initElasticClient() throws IOException {
        InputStream clientSettingsStream = CLASS_LOADER.getResourceAsStream(CLIENT_PROPERTIES_FILE_NAME);
        Properties clientProperties = new Properties();
        clientProperties.load(clientSettingsStream);
        Settings settings = Settings.builder().put(Maps.fromProperties(clientProperties)).build();
        elasticClient = new PreBuiltTransportClient(settings);
        elasticClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), port));
    }
}
