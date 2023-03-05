package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.NewsModel;
import com.mjc.school.repository.model.implementation.TagModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(target = "authorId", source = "authorModel.id")
    @Mapping(target = "tags", source = "tagModel", qualifiedByName = "tagModelToId")
    NewsDtoResponse newsModelToDto(NewsModel newsModel);

    @Named("tagModelToId")
    default List<Long> tagModelToId(List<TagModel> models) {
        List<Long> tagsId = new ArrayList<>();
        if (models != null) {
            models.forEach(model -> tagsId.add(model.getId()));
        }
        return tagsId;
    }

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "authorModel.id", source = "authorId")
    @Mapping(target = "tagModel", source = "tagsId", qualifiedByName = "tagIdToModel")
    NewsModel newsDtoToModel(NewsDtoRequest newsDtoRequest);

    @Named("tagIdToModel")
    default List<TagModel> tagIdToModel(List<Long> tagsId) {
        List<TagModel> tags = new ArrayList<>();
        if (tagsId != null) {
            tagsId.forEach(id -> tags.add(new TagModel(id)));
        }
        return tags;
    }
}
