package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-02T20:15:55+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.2.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDtoResponse authorModelToDto(AuthorModel authorModel) {
        if ( authorModel == null ) {
            return null;
        }

        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse();

        authorDtoResponse.setId( authorModel.getId() );
        authorDtoResponse.setName( authorModel.getName() );
        authorDtoResponse.setCreateDate( authorModel.getCreateDate() );
        authorDtoResponse.setLastUpdateDate( authorModel.getLastUpdateDate() );

        return authorDtoResponse;
    }

    @Override
    public AuthorModel authorDtoToModel(AuthorDtoRequest authorDtoRequest) {
        if ( authorDtoRequest == null ) {
            return null;
        }

        AuthorModel authorModel = new AuthorModel();

        authorModel.setId( authorDtoRequest.getId() );
        authorModel.setName( authorDtoRequest.getName() );

        return authorModel;
    }
}
