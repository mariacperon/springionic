package com.cursosp.projetosp.services.validation;

import com.cursosp.projetosp.domain.Cliente;
import com.cursosp.projetosp.dto.ClienteDTO;
import com.cursosp.projetosp.enums.TipoCliente;
import com.cursosp.projetosp.repositories.ClienteRepository;
import com.cursosp.projetosp.resources.exceptions.FieldMessage;
import com.cursosp.projetosp.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null){
            if(!aux.getId().equals(uriId)){
                list.add(new FieldMessage("email", "Email diferente"));
            }
        }else{
            list.add(new FieldMessage("email", "Email não encontrado no sistema"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFielName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
