package com.example.restApi.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
}
