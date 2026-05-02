package com.example.usuarios.service;

import com.example.usuarios.dto.UsuarioDTO;
import com.example.usuarios.entity.Usuario;
import com.example.usuarios.enums.EstadoUsuario;
import com.example.usuarios.enums.TipoUsuario;
import com.example.usuarios.exception.ResourceNotFoundException;
import com.example.usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void crearUsuario_debeAsignarEstadoActivo() {
        UsuarioDTO dto = new UsuarioDTO(null, "Juan Perez", "juan@test.com", "3001234567", TipoUsuario.PRODUCTOR, null);
        Usuario saved = new Usuario(1L, "Juan Perez", "juan@test.com", "3001234567", TipoUsuario.PRODUCTOR, EstadoUsuario.ACTIVO, null);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(saved);

        UsuarioDTO result = usuarioService.crearUsuario(dto);

        assertEquals(EstadoUsuario.ACTIVO, result.getEstado());
        assertEquals("Juan Perez", result.getNombre());
        verify(usuarioRepository).save(any());
    }

    @Test
    void obtenerUsuario_cuandoExiste_retornaDTO() {
        Usuario usuario = new Usuario(1L, "Ana", "ana@test.com", null, TipoUsuario.ADMIN, EstadoUsuario.ACTIVO, null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioDTO result = usuarioService.obtenerUsuario(1L);

        assertNotNull(result);
        assertEquals("Ana", result.getNombre());
    }

    @Test
    void obtenerUsuario_cuandoNoExiste_lanzaResourceNotFoundException() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.obtenerUsuario(99L));
    }

    @Test
    void eliminarUsuario_cuandoNoExiste_lanzaResourceNotFoundException() {
        when(usuarioRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.eliminarUsuario(99L));
        verify(usuarioRepository, never()).deleteById(any());
    }

    @Test
    void listarTodos_retornaListaDeUsuarios() {
        Usuario u = new Usuario(1L, "Carlos", "carlos@test.com", null, TipoUsuario.PRODUCTOR, EstadoUsuario.ACTIVO, null);
        when(usuarioRepository.findAll()).thenReturn(List.of(u));

        List<UsuarioDTO> result = usuarioService.listarTodos();

        assertEquals(1, result.size());
        assertEquals("Carlos", result.get(0).getNombre());
    }

    @Test
    void actualizarUsuario_cuandoNoExiste_lanzaResourceNotFoundException() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());
        UsuarioDTO dto = new UsuarioDTO(null, "Test", "test@test.com", null, TipoUsuario.ADMIN, null);

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.actualizarUsuario(99L, dto));
    }
}
