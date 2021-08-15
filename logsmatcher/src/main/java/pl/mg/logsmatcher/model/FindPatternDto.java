package pl.mg.logsmatcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class FindPatternDto {

    @NotEmpty
    private String path;

    @NotEmpty
    private String pattern;

}
