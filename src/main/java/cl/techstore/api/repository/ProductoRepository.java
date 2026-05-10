package cl.techstore.api.repository;

import cl.techstore.api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByActivoTrue();
    List<Producto> findAll();
    Optional<Producto> findByIdAndActivoTrue(Long id);

}