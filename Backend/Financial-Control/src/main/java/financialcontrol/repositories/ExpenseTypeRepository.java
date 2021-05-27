package financialcontrol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import financialcontrol.models.ExpenseTypeModel;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseTypeModel, Integer>{
	
	/*SELECT * FROM type_tab where name = <name>*/
	ExpenseTypeModel findByName(String name);
	
}
