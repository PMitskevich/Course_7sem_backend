package com.mitskevich.course_7sem.utils;

import com.mitskevich.course_7sem.model.enums.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getGender();
    }

    @Override
    public Gender convertToEntityAttribute(String gender) {
        if (gender == null) {
            return null;
        }

        return Stream.of(Gender.values())
                .filter(element -> element.getGender().equals(gender))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
