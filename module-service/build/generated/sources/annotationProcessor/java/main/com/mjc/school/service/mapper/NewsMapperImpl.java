package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-02T20:15:55+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.2.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDtoResponse newsModelToDto(NewsModel newsModel) {
        if ( newsModel == null ) {
            return null;
        }

        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();

        newsDtoResponse.setId( newsModel.getId() );
        newsDtoResponse.setTitle( newsModel.getTitle() );
        newsDtoResponse.setContent( newsModel.getContent() );
        newsDtoResponse.setCreateDate( newsModel.getCreateDate() );
        newsDtoResponse.setLastUpdateDate( newsModel.getLastUpdateDate() );

        return newsDtoResponse;
    }

    @Override
    public NewsModel newsDtoToModel(NewsDtoRequest newsDtoRequest) {
        if ( newsDtoRequest == null ) {
            return null;
        }

        NewsModel newsModel = new NewsModel();

        newsModel.setId( newsDtoRequest.getId() );
        newsModel.setTitle( newsDtoRequest.getTitle() );
        newsModel.setContent( newsDtoRequest.getContent() );

        return newsModel;
    }
}
