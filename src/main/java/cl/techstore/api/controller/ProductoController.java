package cl.techstore.api.controller;

import cl.techstore.api.model.Producto;
import cl.techstore.api.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // LISTAR SOLO ACTIVOS
    @GetMapping
    public ResponseEntity<List<Producto>> listarActivos() {
        return ResponseEntity.ok(productoService.listarActivos());
    }

    // LISTAR TODOS INCLUYENDO ELIMINADOS
    @GetMapping("/todos")
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    // BUSCAR POR ID SOLO ACTIVOS
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }
    
    // BUSCAR POR ID INCLUYENDO ELIMINADOS
    @GetMapping("/todos/{id}")
    public ResponseEntity<Producto> obtenerPorIdTodos(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorIdTodos(id));
    }

    // CREAR
    @PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // MODIFICAR
    @PutMapping("/{id}")
    public ResponseEntity<Producto> modificar(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        Producto productoModificado = productoService.modificar(id, producto);
        return ResponseEntity.ok(productoModificado);
    }

    // ELIMINAR LOGICO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}