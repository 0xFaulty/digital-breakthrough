package cloud.socify.controller;

import cloud.socify.model.*;
import cloud.socify.model.filtering.CompanyInfoFilterRequest;
import cloud.socify.model.filtering.StudentFilterRequest;
import cloud.socify.model.filtering.UniversityFilterRequest;
import cloud.socify.service.IUserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user-info")
public class UserInfoController {

    private final IUserInfoService userInfoService;

    @GetMapping("/student")
    public Page<StudentInfo> studentInfo(Pageable pageRequest, StudentFilterRequest filterRequest) {
        return userInfoService.getStudentInfoList(pageRequest, filterRequest);
    }

    @GetMapping("/student/{userId}")
    public StudentInfo studentInfo(@PathVariable Long userId) {
        return userInfoService.getStudentInfo(userId);
    }

    @GetMapping("/company")
    public Page<CompanyInfo> companyInfo(PageRequest pageRequest, CompanyInfoFilterRequest filterRequest) {
        return userInfoService.getCompanyInfoList(pageRequest, filterRequest);
    }

    @GetMapping("/company/{userId}")
    public CompanyInfo companyInfo(@PathVariable Long userId) {
        return userInfoService.getCompanyInfo(userId);
    }

    @GetMapping("/institution")
    public Page<InstitutionInfo> institutionInfo(PageRequest pageable, UniversityFilterRequest filterRequest) {
        return userInfoService.getInstitutionInfoList(pageable, filterRequest);
    }

    @GetMapping("/institution/{userId}")
    public InstitutionInfo institutionInfo(@PathVariable Long userId) {
        return userInfoService.getInstitutionInfo(userId);
    }
}
