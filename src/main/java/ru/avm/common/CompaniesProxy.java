package ru.avm.common;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.avm.common.dto.CompanyDto;

import java.util.List;

@FeignClient(value = "companies-proxy", url = "${app.services.migration}")
public interface CompaniesProxy {

    @GetMapping("/companies/all")
    List<CompanyDto> findAll();

    @GetMapping("/companies/{companyId}")
    CompanyDto findById(@PathVariable Long companyId);

    @GetMapping("/companies/{companyId}/children")
    List<CompanyDto> findCompanyChildren(@PathVariable Long companyId);

    @GetMapping("/companies/{companyId}/parentOf/{childCompanyId}")
    boolean isParentOf(@PathVariable Long companyId, @PathVariable Long childCompanyId);

    @GetMapping("/companies/{companyId}/parents")
    List<CompanyDto> findCompanyParents(@PathVariable Long companyId);

}
