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
import static org.junit.jupiter.api.Assertions.*;

class MascotaService01JUnitTest {

    MascotaService mascotaService;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRegistrarMascotaCorrectamente() {
        // Arrange (Preparar)
        MascotaRepository mascotaRepository = new MascotaRepositoryImpl();
        ExternalService externalService = new ExternalServiceImpl();
        mascotaService = new MascotaService(mascotaRepository, externalService);

        Propietario propietario = new Propietario("Dany","Lima", "987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        // Act (Actuar)
        Mascota registrada = mascotaService.registrarMascota(mascota);

        // Assert (Afirmer) - JUnit

        // Verifica que el objeto no es null.
        assertNotNull(registrada, "La mascota registrada no deberia ser null.");

        // Verifica que dos referencias apunten al mismo objeto
        assertSame(mascota, registrada, "La mascota registrada debería ser la misma que la ingresada.");

        // Verifica que el valor esperado coincida con el actual.
        assertEquals("Garfield", registrada.getNombre(), "El nombre de la mascota registrada debería ser 'Garfield'.");

        // Confirma que el propietario de la mascota registrada es el mismo que se proporcionó.
        assertSame(propietario, registrada.getPropietario(), "El propietario de la mascota registrada deberia ser el mismo que el ingresado.");

        // Comprueba los detalles del propietario para garantizar la precisión de los datos
        assertEquals("Dany", registrada.getPropietario().getNombre(), "El nombre del propietario debería ser 'Dany'.");
        assertEquals("Lima", registrada.getPropietario().getCiudad(), "La ciudad del propietario debería ser 'Lima'.");
        assertEquals("987654321", registrada.getPropietario().getTelefono(), "El teléfono del propietario debería ser '987654321'.");

        // Comprobar con más aserciones de JUnit 5
        assertTrue(registrada.getId() > 0, "ID debe ser positivo");

        // Verificación adicional agrupando varias aserciones. Todas deben pasar, o el test fallará.
        assertAll("Propiedades de la mascota",
                () -> assertEquals("Garfield", registrada.getNombre(), "El nombre debería coincidir."),
                () -> assertNotNull(registrada.getPropietario(), "El propietario no debe ser null."),
                () -> assertEquals("Dany", registrada.getPropietario().getNombre(), "El nombre del propietario debe ser 'Dany'"),
                () -> assertEquals("Lima", registrada.getPropietario().getCiudad(), "La ciudad del propietario debe ser 'Lima'"),
                () -> assertEquals("987654321", registrada.getPropietario().getTelefono(), "El teléfono del propietario debe ser '987654321'")
        );

        // Comprueba que las siguientes operaciones no lanzan excepciones
        assertDoesNotThrow(() -> registrada.getNombre(), "Obtener el nombre de la mascota no debería lanzar una excepción.");
        assertDoesNotThrow(() -> registrada.getPropietario().getCiudad(), "Obtener la ciudad del propietario no deberia lanzar una excepción.");
    }
}





