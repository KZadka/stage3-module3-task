package com.mjc.school.service;

import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.ResourceNotFoundException;
import com.mjc.school.service.implementation.AuthorService;
import com.mjc.school.service.mapper.AuthorMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorService service;

    private AuthorDtoRequest request;
    private AuthorDtoResponse response;
    private AuthorModel model;

    @BeforeEach
    void setUp() {
        request = new AuthorDtoRequest(1L, "TestNameOne");

        model = AuthorMapper.INSTANCE.authorDtoToModel(request);

        response = AuthorMapper.INSTANCE.authorModelToDto(model);
    }

    @Test
    void givenSizeOfListWhenReadAllThenReturnListOfAuthors() {
        long expectedListSize = 2;
        AuthorDtoRequest secondRequest = new AuthorDtoRequest(2L, "TestNameTwo");
        AuthorModel secondTestModel = AuthorMapper.INSTANCE.authorDtoToModel(secondRequest);

        given(repository.readAll()).willReturn(List.of(model, secondTestModel));

        List<AuthorDtoResponse> testResponsesList = service.readAll();

        assertEquals(expectedListSize, testResponsesList.size());

    }

    @Test
    void givenAuthorIdWhenReadAuthorByIdThenReturnAuthorDtoResponse() {
        long expectedId = 1;
        when(repository.readById(expectedId)).thenReturn(Optional.of(model));
        AuthorDtoResponse actualResponse = service.readById(expectedId);

        assertNotEquals(null, actualResponse);
        assertEquals(expectedId, actualResponse.getId());
    }

    @Test
    void givenWrongAuthorIdWhenReadByIdThenThrowsException() {
        long wrongAuthorId = 55;

        assertThrows(ResourceNotFoundException.class, () -> service.readById(wrongAuthorId));
    }

    @Test
    void givenAuthorDtoRequestWhenCreateThenReturnExpectedAuthorDtoResponse() {
        given(repository.create(model)).willReturn(model);
        AuthorDtoResponse actual = service.create(request);

        assertEquals(response, actual);
    }

    @Test
    void givenAuthorDtoRequestWhenUpdateThenReturnUpdatedDtoResponse() {

        Long id = 1L;
        given(repository.update(model)).willReturn(model);
        when(repository.existById(id)).thenReturn(true);
        AuthorDtoResponse expected = response;

        AuthorDtoResponse actual = service.update(new AuthorDtoRequest(
                id,
                "TestNameOne"));


        assertEquals(expected, actual);
    }

    @Test
    void givenWrongAuthorIdWhenUpdateThenThrowException() {
        long wrongAuthorId = 77;

        assertThrows(ResourceNotFoundException.class, () -> service.update(new AuthorDtoRequest(
                wrongAuthorId,
                "Updated name")));
    }

    @Test
    void givenAuthorIdWhenDeleteThenReturnTrue() {
        Long id = 1L;
        given(repository.deleteById(id)).willReturn(true);
        when(repository.existById(id)).thenReturn(true);
        assertTrue(service.deleteById(id));
    }

    @Test
    void givenWrongAuthorIdWhenDeleteThenThrowException() {
        long wrongAuthorId = 53;

        assertThrows(ResourceNotFoundException.class, () -> service.deleteById(wrongAuthorId));
    }
}