package com.oleksii.searcher;

import com.oleksii.dao.KeysDAO;
import com.oleksii.dao.QueriesDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearcherConfiguration {

    @Bean
    public static SearcherService searcherService(QueriesDAO queriesDAO, KeysDAO keysDAO) {
        return new SearcherService(queriesDAO, keysDAO);
    }
}
