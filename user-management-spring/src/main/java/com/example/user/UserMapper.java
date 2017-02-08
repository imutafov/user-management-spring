/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.user;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author msol-pc
 */
public class UserMapper {

    public static List<UserDTO> mapEntitiesIntoDTOs(List<User> users) {
        return users.stream()
                .map(UserMapper::mapEntityIntoDTO)
                .collect(Collectors.toList());
    }

    public static UserDTO mapEntityIntoDTO(User user) {

        UserDTO dto = new UserDTO();
        dto.setUserName(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static Page<UserDTO> mapEntityPageIntoDTOPage(Pageable page, Page<User> source) {
        List<UserDTO> dtos = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, page, source.getTotalElements());
    }
}
