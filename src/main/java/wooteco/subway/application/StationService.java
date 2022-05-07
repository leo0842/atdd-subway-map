package wooteco.subway.application;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wooteco.subway.dao.StationDao;
import wooteco.subway.domain.Station;

@Service
public class StationService {

    private final StationDao stationDao;

    public StationService(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    @Transactional
    public Station createStation(String name) {
        checkExistsName(name);
        return stationDao.save(new Station(name));
    }

    @Transactional(readOnly = true)
    public List<Station> showStations() {
        return stationDao.findAll();
    }

    @Transactional
    public int deleteStation(Long id) {
        return stationDao.deleteById(id);
    }

    private void checkExistsName(String name) {
        if (stationDao.existsByName(name)) {
            throw new IllegalArgumentException("중복된 역 이름입니다.");
        }
    }
}
