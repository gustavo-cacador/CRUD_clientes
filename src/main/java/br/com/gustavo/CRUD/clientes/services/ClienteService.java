package br.com.gustavo.CRUD.clientes.services;

import br.com.gustavo.CRUD.clientes.dto.ClienteDTO;
import br.com.gustavo.CRUD.clientes.entities.Cliente;
import br.com.gustavo.CRUD.clientes.repositories.ClienteRepository;
import br.com.gustavo.CRUD.clientes.services.exceptions.DatabaseException;
import br.com.gustavo.CRUD.clientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public ClienteDTO procurarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente inexistente."));
        return new ClienteDTO(cliente);
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> buscarTodos(Pageable pageable) {
        Page<Cliente> result = clienteRepository.findAll(pageable);
        return result.map(x -> new ClienteDTO(x));
        // return clientes.map(ClienteDTO::new);
    }

    @Transactional
    public ClienteDTO inserirCliente(ClienteDTO dto) {
        Cliente entity = new Cliente();
        copyDtoToEntity(dto, entity);
        entity = clienteRepository.save(entity);
        return new ClienteDTO(entity);
    }

    @Transactional
    public ClienteDTO atualizarCliente(Long id, ClienteDTO dto) {
        try {
            Cliente entity = clienteRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = clienteRepository.save(entity);
            return new ClienteDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente inexistente.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente inexistente");
        } try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
        clienteRepository.deleteById(id);
    }

    private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setRenda(dto.getRenda());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setFilhos(dto.getFilhos());
    }
}
