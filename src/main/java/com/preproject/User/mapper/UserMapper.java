package com.preproject.User.mapper;


import com.preproject.User.dto.UserPatchDto;
import com.preproject.User.dto.UserPostDto;
import com.preproject.User.dto.UserResponseDto;
import com.preproject.User.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User userPostDtoToUser(UserPostDto userPostDto);

    User userPatchDtoToUser(UserPatchDto userPatchDto);
    UserResponseDto userToUserResponseDto(User user);

    List<UserResponseDto> usersToUserResponseDtos(List<User> users);


}
