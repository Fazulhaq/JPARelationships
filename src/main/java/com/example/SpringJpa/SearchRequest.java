package com.example.SpringJpa;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchRequest {
    private String firstname;
    private String lastname;
    private String email;
}
