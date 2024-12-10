package com.alissonmds.LostPets.infra.configurations;

import com.alissonmds.LostPets.domain.models.endereco.Estados;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EstadosConverter implements Converter<String, Estados> {

    @Override
    public Estados convert(String source) {
        return Estados.verificarEstado(source);
    }
}