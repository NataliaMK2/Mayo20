package com.softtek.Mayo20;

import com.softtek.Mayo20.modelo.Mascota;
import com.softtek.Mayo20.modelo.Propietario;
import com.softtek.Mayo20.repository.MascotaRepository;
import com.softtek.Mayo20.repository.MascotaRepositoryImpl;
import com.softtek.Mayo20.service.ExternalService;
import com.softtek.Mayo20.service.ExternalServiceImpl;
import com.softtek.Mayo20.service.MascotaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MascotaService02HamcrestTest {

    MascotaService mascotaService;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRegistrarMascotaCorrectamente() {

        // Arrange (Preparar)

        MascotaRepository mascotaRepository = new MascotaRepositoryImpl();
        ExternalService externalService = new ExternalServiceImpl();
        mascotaService = new MascotaService(mascotaRepository, externalService);

        Propietario propietario = new Propietario("Dany", "Lima", "987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        // Act (Actuar)

        Mascota registrada = mascotaService.registrarMascota(mascota);

        // Assert (Afirmar)

        // Verificar las propiedades de la mascota registrada

        assertThat(registrada, is(notNullValue()));
        assertThat(registrada.getNombre(), is(equalTo("Garfield")));
        assertThat(registrada.getPropietario(), is(notNullValue()));
        assertThat(registrada.getPropietario().getNombre(), is(equalTo("Dany")));
        assertThat(registrada.getPropietario().getCiudad(), is(equalTo("Lima")));
        assertThat(registrada.getPropietario().getTelefono(), is(equalTo("987654321")));
        assertThat(registrada, is(sameInstance(mascota)));
    }




}
