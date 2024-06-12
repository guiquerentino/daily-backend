package br.com.daily.backend.modules.registers;

import br.com.daily.backend.modules.registers.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {

    List<Register> findByOwnerIdAndDataHoraCriacaoBetween(Long ownerId, LocalDateTime startDate, LocalDateTime endDate);

}
