package com.mjc.school.service;

import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.ResourceNotFoundException;
import com.mjc.school.service.implementation.NewsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    @Mock
    private NewsRepository repository;

    @InjectMocks
    private NewsService service;

    @Test
    void givenSizeOfFullListWhenReadAllThenReturnListOfAllNews() {
        int expectedSize = 20;

        List<NewsDtoResponse> newsList = service.readAll();

        assertEquals(expectedSize, newsList.size());
    }

    @Test
    void givenNewsIdWhenReadNewsByIdThenReturnNewsDtoResponse() {
        long expectedId = 5;

        NewsDtoResponse actualResponse = service.readById(expectedId);

        assertNotEquals(null, actualResponse);
        assertEquals(expectedId, actualResponse.getId());
    }

    @Test
    void givenWrongNewsIdWhenReadNewsByIdThenThrowException() {
        long wrongNewsId = 21;

        assertThrows(ResourceNotFoundException.class, () -> service.readById(wrongNewsId));
    }

//    @Test
//    void givenNewsDtoRequestWhenCreateNewsThenReturnExpectedNewsDtoResponse() {
//        NewsDtoResponse expected = new NewsDtoResponse(
//                21L,
//                "Title",
//                "Content",
//                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
//                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
//                10L);
//        NewsDtoRequest request = new NewsDtoRequest(
//                21L,
//                "Title",
//                "Content",
//                10L);
//
//        NewsDtoResponse actual = service.create(request);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void givenNewsDtoRequestWhenUpdateThenReturnUpdatedNewsDtoResponse() {
//        LocalDateTime timeOfCreation = service.readById(1L).getCreateDate();
//        NewsDtoResponse expected = new NewsDtoResponse(
//                1L,
//                "Updated title",
//                "Updated content",
//                timeOfCreation,
//                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
//                2L);
//        NewsDtoResponse actual = service.update(new NewsDtoRequest(
//                1L,
//                "Updated title",
//                "Updated content",
//                2L));
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void givenWrongNewsIdWhenUpdateThenThrowException() {
//        long wrongNewsId = 33;
//
//        assertThrows(ResourceNotFoundException.class, () -> service.update(new NewsDtoRequest(
//                wrongNewsId,
//                "Updated title",
//                "Updated content",
//                2L)));
//    }

    @Test
    void givenNewsIdWhenDeleteThenReturnTrue() {
        assertTrue(service.deleteById(1L));
    }

    @Test
    void givenWrongNewsIdWhenDeleteThenThrowException() {
        long wrongNewsId = 33;

        assertThrows(ResourceNotFoundException.class, () -> service.deleteById(wrongNewsId));
    }
}