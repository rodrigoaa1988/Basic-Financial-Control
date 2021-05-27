package financialcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import financialcontrol.models.PeriodModel;

public interface PeriodRepository extends JpaRepository<PeriodModel, Integer>{

}
