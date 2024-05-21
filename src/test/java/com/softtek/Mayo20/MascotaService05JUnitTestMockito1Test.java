package com.softtek.Mayo20;

import com.softtek.Mayo20.modelo.Mascota;
import com.softtek.Mayo20.modelo.Propietario;
import com.softtek.Mayo20.repository.MascotaRepository;
import com.softtek.Mayo20.service.ExternalService;
import com.softtek.Mayo20.service.MascotaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MascotaService05JUnitTestMockito1Test {

    MascotaService mascotaService;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRegistrarMascotaCorrectamente() {

        // Arrange (Preparar)
        MascotaRepository mascotaRepository = mock(MascotaRepository.class);
        ExternalService externalService = mock(ExternalService.class);

        // Inyección de dependencias manual.
        mascotaService = new MascotaService(mascotaRepository, externalService);

        Propietario propietario = new Propietario();
        propietario.setNombre("Dany");
        propietario.setCiudad("Lima");
        propietario.setTelefono("9876543214");

        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        when(externalService.validarVacunas(any(Mascota.class))).thenReturn(true);
        when(externalService.verificarRegistroMunicipal(any(Mascota.class))).thenReturn(true);
        when(mascotaRepository.findById(any(Integer.class))).thenReturn(Optional.of(mascota));
        when(mascotaRepository.guardar(any(Mascota.class))).thenReturn(mascota);

        Mascota registrada = mascotaService.registrarMascota(mascota);

        // Verifica que el objeto no es null.
        assertNotNull(registrada, "La mascota registrada no debería ser null.");

        // Verifica que dos referencias apunten al mismo objeto.
        assertSame(mascota, registrada, "La mascota registrada debería ser la misma que la mascota original.");
    }
}