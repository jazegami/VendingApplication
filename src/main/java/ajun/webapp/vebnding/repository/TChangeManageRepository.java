package ajun.webapp.vebnding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ajun.webapp.vebnding.entity.TChangeManage;

@Repository
public interface TChangeManageRepository extends JpaRepository<TChangeManage, Integer> {
	
	TChangeManage findByChangeManageId(Long changeManageId);
}
