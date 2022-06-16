package com.ninjaone.catalog.usecase;

import com.ninjaone.catalog.domain.dto.CatalogDTO;
import com.ninjaone.catalog.infra.entity.ServiceEntity;
import com.ninjaone.catalog.infra.repository.ServiceForDeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ServiceUseCaseTest {

    @Mock
    private ServiceForDeviceRepository repository;

    @InjectMocks
    private ServiceUseCase useCase;

    @Test
    public void shouldInsertService() throws Exception {
        CatalogDTO dto = getCatalogDTO();
        ServiceEntity serviceEntity = getServiceEntity(dto);

        when(repository.findById(eq(dto.getItem()))).thenReturn(Optional.of(serviceEntity));
        useCase.insert(dto);

        verify(repository, times(1)).findById(eq(dto.getItem()));
        verify(repository, times(0)).save(any());
    }

    @Test
    public void shouldDeleteService(){

        CatalogDTO dto = getCatalogDTO();
        ServiceEntity serviceEntity = getServiceEntity(dto);

        when(repository.findById(eq(dto.getItem()))).thenReturn(Optional.of(serviceEntity));
        useCase.delete(dto.getItem());

        ServiceEntity unavailableServiceEntity = getServiceEntity(dto);
        unavailableServiceEntity.setAvailable(false);
        verify(repository, times(1)).findById(eq(dto.getItem()));
        verify(repository, times(1)).save(eq(unavailableServiceEntity));

    }


    private ServiceEntity getServiceEntity(CatalogDTO dto) {
        return new ServiceEntity(dto.getItem(), dto.getPrice(), dto.getCompatibility());
    }

    private CatalogDTO getCatalogDTO() {
        return new CatalogDTO("test", 1d, true, "generic");
    }
}
