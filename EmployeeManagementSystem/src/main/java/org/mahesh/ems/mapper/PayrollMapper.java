package org.mahesh.ems.mapper;

import org.mahesh.ems.dto.PayrollResponse;
import org.mahesh.ems.entity.Payroll;

public class PayrollMapper {

    public static PayrollResponse toResponse(Payroll payroll) {

        return new PayrollResponse(
                payroll.getId(),
                payroll.getEmployee().getFirstName() + " " +
                        payroll.getEmployee().getLastName(),
                payroll.getMonth(),
                payroll.getBasicSalary(),
                payroll.getBonus(),
                payroll.getDeduction(),
                payroll.getNetSalary(),
                payroll.getPaymentDate()
        );
    }
}