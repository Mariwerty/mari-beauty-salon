package com.project.maribeauty.service;

import com.project.maribeauty.dto.UserDto;
import com.project.maribeauty.model.Role;
import com.project.maribeauty.model.User;
import com.project.maribeauty.repositories.RoleRepository;
import com.project.maribeauty.repositories.UserRepository;
import com.project.maribeauty.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Test
    public void testSaveUser() {
        // Create mock objects for dependencies
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        RoleRepository mockRoleRepository = Mockito.mock(RoleRepository.class);
        PasswordEncoder mockPasswordEncoder = Mockito.mock(PasswordEncoder.class);

        // Create the class under test, injecting the mocks
        UserServiceImpl userService = new UserServiceImpl(mockUserRepository, mockRoleRepository, mockPasswordEncoder);

        // Create a sample UserDto
        UserDto userDto = new UserDto(1L, "Viktoria",
                "Ivanova", "vikky@mail.com", "111222");

        // Mock password encoding
        Mockito.when(mockPasswordEncoder.encode("111222")).thenReturn("encodedPassword");

        // Mock role repository behavior
        Role role = new Role("ROLE_USER");
        Mockito.when(mockRoleRepository.findByName("ROLE_USER")).thenReturn(role);

        // Call the saveUser method
        UserDto savedUserDto = userService.saveUser(userDto);

        // Verify that the user was saved with expected fields
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(mockUserRepository).save(userArgumentCaptor.capture());
        User savedUser = userArgumentCaptor.getValue();
        assertEquals("Viktoria Ivanova", savedUser.getName());
        assertEquals("vikky@mail.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals(Arrays.asList(role), savedUser.getRoles());

        // Verify that the returned UserDto matches the input
        assertEquals(userDto, savedUserDto);
    }

    @Test
    public void testFindUserByEmail() {
        // Create mock objects
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        User user = new User(1L,"Viktoria Ivanova", "vikky@mail.com", "111222", null);
        Mockito.when(mockUserRepository.findByEmail("vikky@mail.com")).thenReturn(user);

        // Create the class under test
        UserServiceImpl userService = new UserServiceImpl(mockUserRepository, null, null);
        // No need for password encoder or role repository here

        // Call the findUserByEmail method
        User foundUser = userService.findUserByEmail("vikky@mail.com");

        // Assert the result
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindAllUsers() {
        // Create mock objects
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        List<User> users = Arrays.asList(new User(1L,"Viktoria Ivanova",
                "vikky@mail.com", "111222", null));
        Mockito.when(mockUserRepository.findAll()).thenReturn(users);

        // Create the class under test
        UserServiceImpl userService = new UserServiceImpl(mockUserRepository, null, null);

        // Call the findAllUsers method
        List<UserDto> userDtos = userService.findAllUsers();

        // Assert the result size and basic structure
        assertEquals(1, userDtos.size());
        assertEquals("Viktoria", userDtos.get(0).getFirstName());
        assertEquals("Ivanova", userDtos.get(0).getLastName());
        assertEquals("vikky@mail.com", userDtos.get(0).getEmail());
    }
}
