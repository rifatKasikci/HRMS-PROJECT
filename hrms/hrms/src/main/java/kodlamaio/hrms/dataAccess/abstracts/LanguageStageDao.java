package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.LanguageStage;

public interface LanguageStageDao extends JpaRepository<LanguageStage, Integer>{

}
