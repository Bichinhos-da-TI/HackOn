package com.hackon.dto.mappers;

import org.springframework.stereotype.Component;

import com.hackon.dto.UserDto;
import com.hackon.dto.enums.UserRoleEnum;
import com.hackon.dto.request.CreateUserDto;
import com.hackon.entities.User;

@Component
public class UserMapper {
    public UserDto toDto(User user){
        if(user == null){
            return null;
        }
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), toEnum(user.getRole()));
    }

    
    public User toEntity(UserDto userDto)throws Exception{
        if(userDto == null){
            return null;
        }
        return new User(userDto.id(), userDto.name(), userDto.email(), userDto.password(), toEntityEnum(userDto.role()));
    }

    public User toEntity(CreateUserDto createUserDto)throws Exception{
        if(createUserDto == null){
            return null;
        }
        return new User(createUserDto.name(), createUserDto.email(), createUserDto.password(), toEntityEnum(createUserDto.role()));
    }

    public UserRoleEnum toEnum(String role) {
        if(role.equals(UserRoleEnum.ADMIN.toString()))return UserRoleEnum.ADMIN;
        if(role.equals(UserRoleEnum.USER.toString()))return UserRoleEnum.USER;
        throw new IllegalArgumentException("Role inválido: " + role);
    }

    public String toEntityEnum(UserRoleEnum role){
        if(role.equals(UserRoleEnum.ADMIN))return UserRoleEnum.ADMIN.toString();
        if(role.equals(UserRoleEnum.USER))return UserRoleEnum.USER.toString();
        throw new IllegalArgumentException("Role inválido: " + role);
    }
}
