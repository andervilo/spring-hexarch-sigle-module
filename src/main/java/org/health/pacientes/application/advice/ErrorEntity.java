package org.health.pacientes.application.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorEntity {

    private int status;

    private String Error;

    private List<String> message = new ArrayList<>();
}
