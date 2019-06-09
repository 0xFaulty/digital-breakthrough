package cloud.socify.repository;

import cloud.socify.model.StudentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInfoRepository extends PagingAndSortingRepository<StudentInfo, Long> {

    Page<StudentInfo> findAll(Specification<StudentInfo> specification, Pageable pageable);
}
