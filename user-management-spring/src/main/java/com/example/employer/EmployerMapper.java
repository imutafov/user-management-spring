/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employer;

import java.util.List;
import java.util.stream.Collectors;

public class EmployerMapper {

    public static List<EmployerDTOAdmin> mapEntitiesIntoDTOs(List<Employer> employers) {
        return employers.stream()
                .map(EmployerMapper::mapEntityIntoDTO)
                .collect(Collectors.toList());
    }

    public static EmployerDTOAdmin mapEntityIntoDTO(Employer employer) {

        EmployerDTOAdmin dto = new EmployerDTOAdmin();
        dto.setId(employer.getId());
        dto.setEmployes(employer.getEmployees());
        return dto;
    }

}
