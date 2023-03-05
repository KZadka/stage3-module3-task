package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.ResourceNotFoundException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validation.ValidateNewsParam;
import com.mjc.school.service.validation.ValidateNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private static final String NON_EXISTED_ID = "News with that ID does not exist";

    private final BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsRepository.readAll().stream()
                .map(NewsMapper.INSTANCE::newsModelToDto)
                .toList();
    }

    @Override
    @ValidateNumber
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> newsModel = newsRepository.readById(id);
        if (newsModel.isPresent()) {
            return NewsMapper.INSTANCE.newsModelToDto(newsModel.get());
        } else {
            throw new ResourceNotFoundException(2010, NON_EXISTED_ID);
        }
    }

    @Override
    @ValidateNewsParam
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        NewsModel newsModel = newsRepository.create(NewsMapper.INSTANCE.newsDtoToModel(createRequest));
        return NewsMapper.INSTANCE.newsModelToDto(newsModel);
    }

    @Override
    @ValidateNewsParam
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if (newsRepository.existById(updateRequest.getId())) {
            NewsModel newsModel = newsRepository.update(NewsMapper.INSTANCE.newsDtoToModel(updateRequest));
            return NewsMapper.INSTANCE.newsModelToDto(newsModel);
        } else {
            throw new ResourceNotFoundException(2010, NON_EXISTED_ID);
        }
    }

    @Override
    @ValidateNumber
    public boolean deleteById(Long id) {
        if (newsRepository.existById(id)) {
            return newsRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(2010, NON_EXISTED_ID);
        }
    }
}
