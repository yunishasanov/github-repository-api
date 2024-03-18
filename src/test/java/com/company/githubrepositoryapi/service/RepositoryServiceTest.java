package com.company.githubrepositoryapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.company.githubrepositoryapi.dto.RepositoryList;
import com.company.githubrepositoryapi.exception.ThirdpartyServiceException;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class RepositoryServiceTest {

  @Mock
  private RestTemplate restTemplate;

  @Captor
  private ArgumentCaptor<String> urlCaptor;
  @InjectMocks
  private RepositoryService repositoryService;


  @Test
  void getPopularRepositories_ReturnsExpectedRepositories() {
    String language = "java";
    String since = "2023-01-15";
    int count = 10;
    RepositoryList repositoryList = new RepositoryList();
    repositoryList.setItems(Collections.emptyList());

    Mockito.when(restTemplate.getForObject(any(String.class), any(Class.class)))
        .thenReturn(repositoryList);
    repositoryService.getPopularRepositories(count, language, since);
    Mockito.verify(restTemplate).getForObject(urlCaptor.capture(), any(Class.class));
    String capturedUrl = urlCaptor.getValue();
    String expectedUrl = "https://api.github.com/search/repositories?q=created:>=2023-01-15 language:java&per_page=10&sort=stars&order=desc";
    assertEquals(expectedUrl, capturedUrl);
  }

  @Test
  void getPopularRepositories_ReturnsExceptionIfSinceIsInvalid() {
    String language = "java";
    String since = "invalid data";
    int count = 10;
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> repositoryService.getPopularRepositories(count, language, since))
        .withMessage("Incorrect date format: example 2023-01-15 (yyyy-mm-dd)");
  }

  @Test
  void getPopularRepositories_ReturnsExceptionIfServiceReturnsException() {
    String language = "java";
    String since = "2023-01-15";
    int count = 10;
    Mockito.when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(
        ResourceAccessException.class);
    Assertions.assertThatExceptionOfType(ThirdpartyServiceException.class)
        .isThrownBy(() -> repositoryService.getPopularRepositories(count, language, since));
  }


}