package cloud.socify.service;

import cloud.socify.model.*;
import cloud.socify.model.filtering.CompanyInfoFilterRequest;
import cloud.socify.model.filtering.StudentFilterRequest;
import cloud.socify.model.filtering.UniversityFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserInfoService {
    Page<StudentInfo> getStudentInfoList(Pageable pageRequest,
                                         StudentFilterRequest filterRequest);

    Page<InstitutionInfo> getInstitutionInfoList(Pageable pageable,
                                                 UniversityFilterRequest universityFilterRequest);

    Page<CompanyInfo> getCompanyInfoList(Pageable pageRequest,
                                         CompanyInfoFilterRequest filterRequest);

    InstitutionInfo getInstitutionInfo(Long userId);

    CompanyInfo getCompanyInfo(Long userId);

    StudentInfo getStudentInfo(Long userId);
}
