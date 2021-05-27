package financialcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import financialcontrol.models.ExpenseModel;

public interface ExpenseRepository extends JpaRepository<ExpenseModel, Integer>{

}
