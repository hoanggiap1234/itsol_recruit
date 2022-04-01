package com.itsol.recruit.web.vm;

import com.itsol.recruit.core.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WorkingFormVM {
    @NotNull(message = Constants.ValidationMessage.FIELD_IS_REQUIRED)
    String code;

    @NotNull(message = Constants.ValidationMessage.FIELD_IS_REQUIRED)
    String description;
}
