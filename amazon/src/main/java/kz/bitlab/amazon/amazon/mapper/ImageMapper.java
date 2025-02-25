package kz.bitlab.amazon.amazon.mapper;

import kz.bitlab.amazon.amazon.dto.ImageDto;
import kz.bitlab.amazon.amazon.models.Image;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageDto toDto(Image image);
    Image toEntity(ImageDto imageDto);
    List<ImageDto> toDtoList(List<Image> images);
}
