package pe.edu.upeu.asistencia.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pe.edu.upeu.asistencia.models.Periodo;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class PeriodoRepositoryTest {
    @Autowired
    PeriodoRepository periodoRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {
        //List<Periodo> local=periodoRepository.findAll();
        //if (local.isEmpty()) {
        Periodo periodo = new Periodo();
        periodo.setNombre("2021-2");
        periodo.setEstado("Inactivo");
        testEntityManager.persist(periodo);
        //}
    }

    @Test
    public void CrearPeriodoTest() {
        //given
        Periodo periodo = new Periodo();
        periodo.setNombre("2021-2");
        periodo.setEstado("Inactivo");
        //When
        Periodo p = periodoRepository.save(periodo);
        //given
        assertThat(p).isNotNull();
        Assertions.assertEquals(p.getNombre(), "2021-2");

    }

    @Test
    public void testListarPeriodo() {
        //Given

        //when
        List<Periodo> lista = periodoRepository.findAll();
        for (Periodo periodo : lista) {
            System.out.println(periodo.getNombre());
        }
        //then
        Assertions.assertEquals(lista.size(), 3);
    }

    @Test
    public void testActualizarPeriodo() {
        Periodo px = periodoRepository.findById(34L).get();
        px.setEstado("Inactivo");
        Periodo p = testEntityManager.persist(px);
        assertThat(p).isNotNull();
        Assertions.assertEquals(p.getEstado(), "Inactivo");

        List<Periodo> lista = periodoRepository.findAll();
        for (Periodo periodo : lista) {
            System.out.println(periodo.getId() + " " + periodo.getEstado());
        }
    }

    @Test
    public void testEliminarPeriodo() {
        Periodo px = periodoRepository.findById(34L).get();
        periodoRepository.delete(px);
        List<Periodo> lista = periodoRepository.findAll();
        for (Periodo periodo : lista) {
            System.out.println(periodo.getId() + " " + periodo.getEstado());
        }

    }

    @Test
    public void testBuscarPeriodo() {
        Periodo px = periodoRepository.findById(34L).get();
        System.out.println(px.getNombre()+ " " + px.getId());

    }


}