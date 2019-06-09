package cloud.socify.controller;

import cloud.socify.model.CompanyInfo;
import cloud.socify.model.InstitutionInfo;
import cloud.socify.model.StudentInfo;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user-info")
public class UserInfoController {

    @GetMapping("/student")
    public Page<StudentInfo> studentInfo() {
        return Page.empty();
    }

    @GetMapping("/student/{userId}")
    public StudentInfo studentInfo(@PathVariable String userId) {
        return new StudentInfo();
    }

    @GetMapping("/company")
    public Page<CompanyInfo> companyInfo() {
        return Page.empty();
    }

    @GetMapping("/company/{userId}")
    public CompanyInfo companyInfo(@PathVariable String userId) {
        return new CompanyInfo();
    }

    @GetMapping("/institution")
    public Page<InstitutionInfo> institutionInfo() {
        return Page.empty();
    }

    @GetMapping("/institution/{userId}")
    public InstitutionInfo institutionInfo(@PathVariable String userId) {
        return new InstitutionInfo();
    }
}
