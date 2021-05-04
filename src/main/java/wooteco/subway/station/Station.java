package wooteco.subway.station;

import java.util.Objects;

public class Station {
    private Long id;
    private String name;

    public Station() {
    }

    public Station(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(final String name) {
        this.name = name;
    }

    public final boolean isId(final Long id) {
        return this.id.equals(id);
    }

    public final boolean sameName(final Station other) {
        return this.name.equals(other.name);
    }

    public final Long getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Station station = (Station) o;
        return id.equals(station.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

