package itmo.project.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class Entry implements Serializable {
    private double x;
    private double y;
    private double r;
    private String currentTime;
    private String responseTime;
    private boolean inRange;
}
