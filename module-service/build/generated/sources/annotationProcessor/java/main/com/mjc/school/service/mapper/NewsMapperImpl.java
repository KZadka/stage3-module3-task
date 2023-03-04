package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.NewsModel;
import com.mjc.school.repository.model.implementation.TagModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-04T21:11:05+0100",
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
        List<TagModel> list = newsModel.getTags();
        if ( list != null ) {
            newsDtoResponse.setTags( new ArrayList<TagModel>( list ) );
        }

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
