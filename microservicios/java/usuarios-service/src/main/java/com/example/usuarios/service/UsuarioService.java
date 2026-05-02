package com.example.usuarios.service;

import com.example.usuarios.dto.UsuarioDTO;
import com.example.usuarios.entity.Usuario;
import com.example.usuarios.enums.EstadoUsuario;
import com.example.usuarios.enums.TipoUsuario;
import com.example.usuarios.exception.ResourceNotFoundException;
import com.example.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setTipoUsuario(dto.getTipoUsuario());
        usuario.setEstado(EstadoUsuario.ACTIVO);
        return convertToDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO obtenerUsuario(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado"));
    }

    public Page<UsuarioDTO> listarTodos(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(this::convertToDTO);
    }

    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado"));
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setTipoUsuario(dto.getTipoUsuario());
        if (dto.getEstado() != null) usuario.setEstado(dto.getEstado());
        return convertToDTO(usuarioRepository.save(usuario));
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario con ID " + id + " no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public Page<UsuarioDTO> listarPorTipo(TipoUsuario tipoUsuario, Pageable pageable) {
        return usuarioRepository.findByTipoUsuario(tipoUsuario, pageable).map(this::convertToDTO);
    }

    private UsuarioDTO convertToDTO(Usuario u) {
        return new UsuarioDTO(u.getId(), u.getNombre(), u.getEmail(),
                u.getTelefono(), u.getTipoUsuario(), u.getEstado());
    }
}
