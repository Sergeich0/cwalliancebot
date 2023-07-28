package me.sergeich0.cwalliancebot.converter;

import java.util.List;

public interface Converter<E, D> {
    E toEntity(D dto);

    D toDTO(E entity);

    default List<E> toEntityList(List<D> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    default List<D> toDTOList(List<E> entityList) {
        return entityList.stream()
                .map(this::toDTO)
                .toList();
    }
}
