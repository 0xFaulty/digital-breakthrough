package cloud.socify.service;

import cloud.socify.entity.User;
import cloud.socify.entity.UserType;
import cloud.socify.exception.UserNotFoundException;
import cloud.socify.model.CompanyInfo;
import cloud.socify.model.InstitutionInfo;
import cloud.socify.model.StudentInfo;
import cloud.socify.model.filtering.CompanyInfoFilterRequest;
import cloud.socify.model.filtering.StudentFilterRequest;
import cloud.socify.model.filtering.UniversityFilterRequest;
import cloud.socify.repository.CompanyInfoRepository;
import cloud.socify.repository.InstitutionInfoRepository;
import cloud.socify.repository.StudentInfoRepository;
import cloud.socify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final UserRepository userRepository;

    @Override
    @PreAuthorize("isAuthenticated()")
    public Page<StudentInfo> getStudentInfoList(Pageable pageRequest,
                                                StudentFilterRequest filterRequest) {

        Specification<StudentInfo> specification = processFilters(filterRequest);

        return studentInfoRepository.findAll(specification, pageRequest);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Page<InstitutionInfo> getInstitutionInfoList(Pageable pageable,
                                                        UniversityFilterRequest universityFilterRequest) {

        Specification<InstitutionInfo> specification = processUniversityFilters(universityFilterRequest);

        return institutionInfoRepository.findAll(specification, pageable);
    }

    @SuppressWarnings("Duplicates")
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
    @PreAuthorize("isAuthenticated()")
    public Page<CompanyInfo> getCompanyInfoList(Pageable pageRequest,
                                                CompanyInfoFilterRequest filterRequest) {

        Specification<CompanyInfo> specification = processCompanyInfoFilters(filterRequest);

        return companyInfoRepository.findAll(specification, pageRequest);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public InstitutionInfo getInstitutionInfo(Long userId) {

        return institutionInfoRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public CompanyInfo getCompanyInfo(Long userId) {
        return companyInfoRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public StudentInfo getStudentInfo(Long userId) {
        return studentInfoRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    @PreAuthorize("hasAuthority('STUDENT')")
    public void saveStudentInfo(StudentInfo studentInfo) {

        Long userId = studentInfo.getUserId();

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        if (user.getUserType() != UserType.STUDENT) {
            throw new IllegalArgumentException("User is not student");
        }

        studentInfoRepository.save(studentInfo);
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
