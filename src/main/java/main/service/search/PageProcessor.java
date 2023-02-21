package main.service.search;

import main.dto.FoundPage;
import main.dto.PageDTO;
import lombok.AllArgsConstructor;
import main.service.MorphologyService;

import java.util.*;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PageProcessor extends RecursiveTask<List<FoundPage>> {

    private List<PageDTO> pagesToProcess;
    private List<String> searchQuery;
    private double maxRelevance;
    private int chunkSize;
    private MorphologyService morphologyService;

    @Override
    protected List<FoundPage> compute() {
        if (pagesToProcess.size() <= chunkSize) {
            return processPages();
        } else {
            return ForkJoinTask.invokeAll(splitTasks())
                    .stream()
                    .map(ForkJoinTask::join)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        }
    }

    List<PageProcessor> splitTasks() {
        List<PageProcessor> subtasks = new ArrayList<>();
        for (int i = 0; i < pagesToProcess.size(); i += chunkSize) {
            int endIndex = Math.min(i + chunkSize, pagesToProcess.size());
            subtasks.add(new PageProcessor(pagesToProcess.subList(i, endIndex), searchQuery, maxRelevance, chunkSize, morphologyService));
        }
        return subtasks;
    }

    List<FoundPage> processPages() {
        List<FoundPage> result = new ArrayList<>();
        for (PageDTO page : pagesToProcess) {
            result.add(new PageDtoAdapter(page, maxRelevance, searchQuery, morphologyService));
        }
        return result;
    }
}
