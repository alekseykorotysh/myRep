package com.oleksii.searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.oleksii.dao.KeysDAO;
import com.oleksii.dao.QueriesDAO;
import com.oleksii.model.Keys;
import lombok.AllArgsConstructor;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

@AllArgsConstructor
public class SearcherService {

    private final QueriesDAO queriesDAO;
    private final KeysDAO keysDAO;

    public Optional<Keys> findResult(String query) {
        List<String> keyWords = new ArrayList<>();
        List<Keys> keys = new ArrayList<>();
        keysDAO.findAll().forEach(keys::add);
        for (Keys si : keys) {
            keyWords.add(si.getKeyWord());
        }

        Optional<ExtractedResult> results = FuzzySearch.extractSorted(query, keyWords, 3)
                                                       .stream()
                                                       .filter(extractedResult -> extractedResult.getScore() > 80)
                                                       .findFirst();
        if (results.isPresent()) {
            Optional<Keys> keyForLink = keys.stream().filter(keys1 -> keys1.getKeyWord() == results.get().getString()).findFirst();
            return keyForLink;
        }
        return Optional.empty();
    }
}
