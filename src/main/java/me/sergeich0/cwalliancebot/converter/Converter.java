package me.sergeich0.cwalliancebot.converter;

import java.util.List;

public interface Converter<E, D> {
    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntityList(List<D> dtoList);

    List<D> toDTOList(List<E> entityList);
}
