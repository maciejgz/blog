package pl.mg.logsmatcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class MatchingResult {

    private long numberOfLines;

    private ArrayList<String> lines;
}
