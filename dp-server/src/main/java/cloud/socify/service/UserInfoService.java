package cloud.socify.service;

import cloud.socify.exception.UserNotFoundException;
import cloud.socify.model.*;
import cloud.socify.model.filtering.CompanyInfoFilterRequest;
import cloud.socify.model.filtering.StudentFilterRequest;
import cloud.socify.model.filtering.UniversityFilterRequest;
import cloud.socify.repository.CompanyInfoRepository;
import cloud.socify.repository.InstitutionInfoRepository;
import cloud.socify.repository.StudentInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoService implements IUserInfoService {

    private final StudentInfoRepository studentInfoRepository;

    private final CompanyInfoRepository companyInfoRepository;

    private final InstitutionInfoRepository institutionInfoRepository;

    @Override
    public Page<StudentInfo> getStudentInfoList(Pageable pageRequest,
                                                StudentFilterRequest filterRequest) {

        Specification<StudentInfo> specification = processFilters(filterRequest);

        return studentInfoRepository.findAll(specification, pageRequest);
    }

    @Override
    public Page<InstitutionInfo> getInstitutionInfoList(Pageable pageable,
                                                        UniversityFilterRequest universityFilterRequest) {

        Specification<InstitutionInfo> specification = processUniversityFilters(universityFilterRequest);

        return institutionInfoRepository.findAll(specification, pageable);
    }

    private Specification<InstitutionInfo> processUniversityFilters(UniversityFilterRequest universityFilterRequest) {
        List<Specification<InstitutionInfo>> specificationList = new ArrayList<>();

        if (universityFilterRequest.getRating() != null) {
            specificationList.add((r, qq, cb) -> cb.equal(r.get("rating"), universityFilterRequest.getRating()));
        }

        return collectSpecifications(specificationList);
    }

    private <T> Specification<T> collectSpecifications(List<Specification<T>> specificationList) {
        Specification<T> specification = null;
        for (Specification<T> spec : specificationList) {
            if (specification == null) {
                specification = spec;
            } else {
                specification = specification.and(spec);
            }
        }
        return specification;
    }

    @Override
    public Page<CompanyInfo> getCompanyInfoList(Pageable pageRequest,
                                                CompanyInfoFilterRequest filterRequest) {

        Specification<CompanyInfo> specification = processCompanyInfoFilters(filterRequest);

        return companyInfoRepository.findAll(specification, pageRequest);
    }

    @Override
    public InstitutionInfo getInstitutionInfo(Long userId) {

        return institutionInfoRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public CompanyInfo getCompanyInfo(Long userId) {
        return companyInfoRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public StudentInfo getStudentInfo(Long userId) {
        return studentInfoRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @SuppressWarnings("Duplicates")
    private Specification<CompanyInfo> processCompanyInfoFilters(CompanyInfoFilterRequest filterRequest) {
        List<Specification<CompanyInfo>> specificationList = new ArrayList<>();

        if (filterRequest.getRating() != null) {
            specificationList.add((r, qq, cb) -> cb.equal(r.get("rating"), filterRequest.getRating()));
        }

        return collectSpecifications(specificationList);
    }

    private Specification<StudentInfo> processFilters(StudentFilterRequest filterRequest) {
        List<Specification<StudentInfo>> specificationList = new ArrayList<>();

        if (filterRequest.getMinAge() != null) {
            LocalDate minBirthDate = LocalDate.now().minusYears(filterRequest.getMinAge());
            specificationList.add((r, qq, cb) -> cb.lessThanOrEqualTo(r.get("birthDate"), minBirthDate));
        }

        if (filterRequest.getMinAge() != null) {
            LocalDate minBirthDate = LocalDate.now().minusYears(filterRequest.getMaxAge());
            specificationList.add((r, qq, cb) -> cb.greaterThanOrEqualTo(r.get("birthDate"), minBirthDate));
        }

        if (filterRequest.getMinRating() != null) {
            specificationList.add((r, qq, cb) -> cb.greaterThanOrEqualTo(r.get("rating"), filterRequest.getMinRating()));
        }

        if (filterRequest.getMaxRating() != null) {
            specificationList.add((r, qq, cb) -> cb.lessThanOrEqualTo(r.get("rating"), filterRequest.getMaxRating()));
        }

        if (filterRequest.getUniversityId() != null) {
            specificationList.add((r, qq, cb) -> cb.equal(r.get("universityId"), filterRequest.getUniversityId()));
        }

        if (filterRequest.getUniversityId() != null) {
            specificationList.add((r, qq, cb) -> cb.equal(r.get("gender"), filterRequest.getGender()));
        }

        return collectSpecifications(specificationList);
    }
}
