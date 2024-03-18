package com.company.githubrepositoryapi.controller;

import com.company.githubrepositoryapi.dto.Repository;
import com.company.githubrepositoryapi.service.RepositoryService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RepositoryController {

  private final RepositoryService repositoryService;
  private final Set<Integer> allowedCounts = new HashSet<>(Arrays.asList(10, 50, 100));

  @GetMapping("/repositories")
  public List<Repository> getPopularRepositories(
      @RequestParam(defaultValue = "10") int count,
      @RequestParam(required = false) String language,
      @RequestParam(required = false) String since
  ) {
    if (allowedCounts.contains(count)) {
      return repositoryService.getPopularRepositories(count, language, since);
    } else {
      throw new IllegalArgumentException("Only top 10, 50, 100 repositories could be requested.");
    }

  }
}
