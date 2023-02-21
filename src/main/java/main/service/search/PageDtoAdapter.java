package main.service.search;

import main.dto.FoundPage;
import main.dto.PageDTO;
import main.service.MorphologyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class PageDtoAdapter extends FoundPage {
    public PageDtoAdapter(PageDTO page, double maxRelevance, List<String> searchQuery, MorphologyService ms) {

        Document content = Jsoup.parse(page.getContent());

        this.setSite(page.getSiteUrl());
        this.setSiteName(page.getSiteName());
        this.setUri(page.getPath());
        this.setTitle(content.select("title").text());
        this.setSnippet(SnippetBuilder.getSnippetFromPage(ms, content.select("body").text(), searchQuery));
        this.setRelevance(page.getRelevance() / maxRelevance);
    }
}
