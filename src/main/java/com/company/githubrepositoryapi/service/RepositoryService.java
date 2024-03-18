package com.company.githubrepositoryapi.service;

import com.company.githubrepositoryapi.dto.Repository;
import com.company.githubrepositoryapi.dto.RepositoryList;
import com.company.githubrepositoryapi.exception.ThirdpartyServiceException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RepositoryService {

  private static final String GITHUB_API_URL = "https://api.github.com/search/repositories";
  private static final String SINCE = "?q=created:>=";
  private static final String LANGUAGE = " language:";
  private static final String PER_PAGE = "&per_page=";
  private static final String SORT_BY = "&sort=stars&order=desc";

  private final RestTemplate restTemplate;

  public List<Repository> getPopularRepositories(int count, String language, String since) {
    val url = buildUrl(count, language, since);
    try {
      val response = restTemplate.getForObject(url, RepositoryList.class);
      return response.getItems();
    } catch (Exception exc) {
      throw new ThirdpartyServiceException(exc.getMessage());
    }
  }

  private String buildUrl(int count, String language, String since) {
    val urlBuilder = new StringBuilder(GITHUB_API_URL);
    if (StringUtils.isNotEmpty(since)) {
      try {
        val sinceDate = LocalDate.parse(since.trim(), DateTimeFormatter.ISO_DATE);
        urlBuilder.append(SINCE).append(sinceDate);
      } catch (DateTimeParseException exc) {
        throw new IllegalArgumentException(
            "Incorrect date format: example 2023-01-15 (yyyy-mm-dd)");
      }
    } else {
      urlBuilder.append(SINCE).append(Instant.EPOCH);
    }
    if (language != null && !language.isEmpty()) {
      urlBuilder.append(LANGUAGE).append(language);
    }
    urlBuilder.append(PER_PAGE).append(count);
    urlBuilder.append(SORT_BY);
    return urlBuilder.toString();
  }
}
