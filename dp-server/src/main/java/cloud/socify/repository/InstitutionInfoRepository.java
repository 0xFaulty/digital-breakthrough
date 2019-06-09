package cloud.socify.repository;

import cloud.socify.model.InstitutionInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionInfoRepository extends PagingAndSortingRepository<InstitutionInfo, Long> {
    Page<InstitutionInfo> findAll(Specification<InstitutionInfo> specification, Pageable pageable);
}
