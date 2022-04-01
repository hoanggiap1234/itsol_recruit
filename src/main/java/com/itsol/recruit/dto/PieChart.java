package com.itsol.recruit.dto;

import lombok.Data;

@Data
public class PieChart {
    String totalApplicant;
    String sucessApplicantQuantity;
    String sucessApplicantRatio;
    String failApplicantQuantity;
    String failApplicantRatio;
}
