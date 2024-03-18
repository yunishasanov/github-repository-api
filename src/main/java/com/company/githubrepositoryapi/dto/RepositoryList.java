package com.company.githubrepositoryapi.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositoryList {

  private int totalCount;
  private boolean incompleteResults;
  private List<Repository> items;
}
