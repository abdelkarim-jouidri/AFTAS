package com.example.aftas.Entities.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponse<T, String> {
    private String msg;
    private T data;

}
