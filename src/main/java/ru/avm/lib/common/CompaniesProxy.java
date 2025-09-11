package ru.avm.lib.common;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.avm.lib.common.dto.CompanyDto;

import java.util.List;

@FeignClient(value = "companies-proxy", url = "${app.services.migration}")
public interface CompaniesProxy {

    @GetMapping("companies/all")
    List<CompanyDto> findAll();

    @GetMapping("companies/{companyId}")
    CompanyDto findById(@PathVariable("companyId") Long companyId);

    @GetMapping("companies/{companyId}/children")
    List<CompanyDto> findCompanyChildren(@PathVariable("companyId") Long companyId);

    @GetMapping("companies/{companyId}/parentOf/{childCompanyId}")
    boolean isParentOf(@PathVariable("companyId") Long companyId, @PathVariable("childCompanyId") Long childCompanyId);

    @GetMapping("companies/{companyId}/parents")
    List<CompanyDto> findCompanyParents(@PathVariable("companyId") Long companyId);

}
