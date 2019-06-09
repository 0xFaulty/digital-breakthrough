package cloud.socify.repository;

import cloud.socify.model.CompanyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoRepository extends PagingAndSortingRepository<CompanyInfo, Long> {

    Page<CompanyInfo> findAll(Specification<CompanyInfo> specification, Pageable pageRequest);
}
