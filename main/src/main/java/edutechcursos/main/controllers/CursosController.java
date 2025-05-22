package edutechcursos.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edutechcursos.main.models.Curso;
import edutechcursos.main.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("")
    public List<Curso> traerCursos() {
        return cursoService.obtenerCursos();
    }


    @PostMapping("/crear")
    public ResponseEntity<String> crearCurso(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.crearCurso(curso));
    }
    
    @GetMapping("/{codigoCurso}")
    public ResponseEntity<Curso> traerCursoCodigo(@PathVariable String codigoCurso) {
        Curso curso = cursoService.obtenerCurso(codigoCurso);
        if (curso != null) {
            return ResponseEntity.ok(curso);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/actualizar/{idCurso}")
    public ResponseEntity<String> actualizarCurso(@PathVariable Integer idCurso, @RequestBody Curso cursoActualizado) {
        boolean actualizado = cursoService.actualizarCurso(idCurso, cursoActualizado);
        if (actualizado) {
            return ResponseEntity.ok("Curso actualizado correctamente");
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/borrar/{idCurso}")
    public ResponseEntity<String> borrarCurso(@PathVariable Integer idCurso) {
        boolean borrado = cursoService.borrarCurso(idCurso);
        return borrado ? ResponseEntity.ok("Curso borrado correctamente") : ResponseEntity.notFound().build();
    }
}
