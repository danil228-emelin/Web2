package itmo.project.bean;

import java.io.Serializable;

public record Entry(double x, double y, double r, String currentTime, String responseTime,
                    boolean inRange) implements Serializable {
}
