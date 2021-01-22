package com.example.demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadDto {

    @NotBlank
    private String title;
    @NotBlank
    private String initialMessage;
}
