package com.masivian.ruleta.controller;

import com.masivian.ruleta.dto.ApuestaDto;
import com.masivian.ruleta.model.Apuesta;
import com.masivian.ruleta.model.Color;
import com.masivian.ruleta.model.Estado;
import com.masivian.ruleta.model.Ruleta;
import com.masivian.ruleta.repository.ApuestaRepository;
import com.masivian.ruleta.repository.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/ruletas/{idRuleta}/apuestas")
public class ApuestaController {

    ApuestaRepository apuestaRepository;
    RuletaRepository ruletaRepository;

    @Autowired
    public ApuestaController(ApuestaRepository apuestaRepository, RuletaRepository ruletaRepository) {
        this.apuestaRepository = apuestaRepository;
        this.ruletaRepository = ruletaRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void crearApuesta(@PathVariable(value = "idRuleta") Integer idRuleta,
                             @RequestBody @Validated ApuestaDto apuestaDto, @RequestHeader String idUsuario){
        Ruleta ruleta = verificarRuletaExistente(idRuleta);

        if(ruleta.getEstado() == Estado.abierta){

            if (apuestaDto.getDinero().compareTo(BigDecimal.valueOf(0)) == 0) {

                throw new IllegalArgumentException("No se puede crear la apuesta en la ruleta de ID " + idRuleta
                        + " porque el dinero apostado es 0");

            }else if (idUsuario == null || idUsuario == "") {

                throw new IllegalArgumentException("No se puede crear la apuesta en la ruleta de ID " + idRuleta
                        + " porque no se proporcionaron los datos del usuario");

            }else{

                if (apuestaDto.getNumero() != null) {

                    apuestaRepository.save(new Apuesta(apuestaDto.getNumero(), Color.ninguno, apuestaDto.getDinero(),
                            idUsuario, ruleta.getId()));

                } else if (apuestaDto.getColor() != null && (apuestaDto.getColor().equals(Color.negro.toString())
                        || apuestaDto.getColor().equals(Color.rojo.toString())) ) {

                    apuestaRepository.save(new Apuesta(null, Color.valueOf(apuestaDto.getColor()), apuestaDto.getDinero(),
                            idUsuario, ruleta.getId()));

                }else{
                    throw new IllegalArgumentException("No se puede crear la apuesta en la ruleta de ID " + idRuleta
                            + " porque el color debe ser negro o rojo y el numero debe estar entre 0 y 36");
                }

            }

        }else{
            throw new IllegalStateException("No se pueden crear apuestas en la ruleta de ID " + idRuleta
                    + " porque no está en estado abierta");
        }
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public List<ApuestaDto> cerrarApuestas(@PathVariable(value = "idRuleta") Integer idRuleta){
        Ruleta ruleta = verificarRuletaExistente(idRuleta);

        if(ruleta.getEstado() == Estado.abierta){
            ruleta.setEstado(Estado.cerrada);
            ruletaRepository.save(ruleta);

            return apuestaRepository.findAllByRuleta(idRuleta).stream().map(a -> toDto(a)).collect(Collectors.toList());
        }else{
            throw new IllegalStateException("No se pueden cerrar las apuestas de la ruleta de ID " + idRuleta
                    + " porque no está en estado abierta");
        }
    }

    private Ruleta verificarRuletaExistente(Integer idRuleta){
        return ruletaRepository.findById(idRuleta)
                .orElseThrow(() -> new NoSuchElementException("No existe una Ruleta de ID " + idRuleta));
    }

    private ApuestaDto toDto(Apuesta apuesta){
        return new ApuestaDto(apuesta.getId(), apuesta.getNumero(), apuesta.getColor().toString(), apuesta.getDinero(),
                apuesta.getIdUsuario(), apuesta.getRuleta());
    }

}
