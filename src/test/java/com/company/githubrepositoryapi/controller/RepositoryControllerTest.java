package com.company.githubrepositoryapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.company.githubrepositoryapi.dto.Repository;
import com.company.githubrepositoryapi.service.RepositoryService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RepositoryControllerTest {

  @Mock
  private RepositoryService repositoryService;

  @InjectMocks
  private RepositoryController repositoryController;


  @Test
  void getPopularRepositories_ValidCount_ReturnsRepositories() {
    int count = 10;
    String language = "java";
    String since = "2023-01-15";
    List<Repository> expectedRepositories = Arrays.asList(new Repository(), new Repository());
    Mockito.when(repositoryService.getPopularRepositories(count, language, since))
        .thenReturn(expectedRepositories);
    List<Repository> actualRepositories = repositoryController.getPopularRepositories(count,
        language, since);
    assertEquals(expectedRepositories, actualRepositories);
  }

  @Test
  void getPopularRepositories_InvalidCount_ThrowsException() {
    int count = 5;
    String language = "java";
    String since = "2023-01-15";
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      repositoryController.getPopularRepositories(count, language, since);
    });
    assertEquals("Only top 10, 50, 100 repositories could be requested.", exception.getMessage());
  }

}