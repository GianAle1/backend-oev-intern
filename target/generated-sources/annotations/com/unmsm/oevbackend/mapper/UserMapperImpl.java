package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.request.UpdateUserRequestDTO;
import com.unmsm.oevbackend.dto.request.UserRegisterRequestDTO;
import com.unmsm.oevbackend.dto.response.AuthUserResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-27T15:38:17-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User requestDTOToEntity(UserRegisterRequestDTO userRegisterRequestDTO) {
        if ( userRegisterRequestDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( userRegisterRequestDTO.getName() );
        user.paternalSurname( userRegisterRequestDTO.getPaternalSurname() );
        user.maternalSurname( userRegisterRequestDTO.getMaternalSurname() );
        user.email( userRegisterRequestDTO.getEmail() );
        user.password( userRegisterRequestDTO.getPassword() );
        user.phone( userRegisterRequestDTO.getPhone() );
        user.role( userRegisterRequestDTO.getRole() );

        return user.build();
    }

    @Override
    public UserResponseDTO entityToDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO.UserResponseDTOBuilder userResponseDTO = UserResponseDTO.builder();

        userResponseDTO.id( user.getId() );
        userResponseDTO.name( user.getName() );
        userResponseDTO.paternalSurname( user.getPaternalSurname() );
        userResponseDTO.maternalSurname( user.getMaternalSurname() );
        userResponseDTO.email( user.getEmail() );
        userResponseDTO.phone( user.getPhone() );
        userResponseDTO.role( user.getRole() );

        return userResponseDTO.build();
    }

    @Override
    public List<UserResponseDTO> entityListToDTOList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseDTO> list = new ArrayList<UserResponseDTO>( users.size() );
        for ( User user : users ) {
            list.add( entityToDTO( user ) );
        }

        return list;
    }

    @Override
    public User updateUserDTOToEntity(UpdateUserRequestDTO updateUserRequestDTO) {
        if ( updateUserRequestDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( updateUserRequestDTO.getId() );
        user.name( updateUserRequestDTO.getName() );
        user.paternalSurname( updateUserRequestDTO.getPaternalSurname() );
        user.maternalSurname( updateUserRequestDTO.getMaternalSurname() );
        user.email( updateUserRequestDTO.getEmail() );
        user.phone( updateUserRequestDTO.getPhone() );

        return user.build();
    }

    @Override
    public AuthUserResponseDTO entityToAuthUserResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        AuthUserResponseDTO.AuthUserResponseDTOBuilder authUserResponseDTO = AuthUserResponseDTO.builder();

        authUserResponseDTO.id( user.getId() );
        authUserResponseDTO.name( user.getName() );
        authUserResponseDTO.paternalSurname( user.getPaternalSurname() );
        authUserResponseDTO.maternalSurname( user.getMaternalSurname() );
        authUserResponseDTO.email( user.getEmail() );
        authUserResponseDTO.phone( user.getPhone() );
        authUserResponseDTO.role( user.getRole() );

        return authUserResponseDTO.build();
    }
}
