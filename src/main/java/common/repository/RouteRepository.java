package common.repository;

import common.dto.SearchDTO;
import common.model.RouteEntity;
import common.model.TrainEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RouteRepository {

    private final Logger logger = LoggerFactory.getLogger(RouteRepository.class);

    @PersistenceContext
    private EntityManager entityManager;


    public boolean checkIfRouteTimeIsCorrect(TrainEntity trainEntity, SearchDTO searchDTO){
        LocalDateTime trainPassSearchStartStation = findTimeTrainOnStation(trainEntity, searchDTO.getStartStationId());
        LocalDateTime trainPassSearchEndStation = findTimeTrainOnStation(trainEntity, searchDTO.getEndStationId());

        LocalDateTime searchStartTime = LocalDateTime.parse(searchDTO.getStartDateTime().toString());
        LocalDateTime searchEndTime = LocalDateTime.parse(searchDTO.getEndDateTime().toString());

        logger.debug("Route time check was {} for {} and {} ", (trainPassSearchStartStation.isAfter(searchStartTime) || trainPassSearchStartStation.equals(searchStartTime)) && trainPassSearchEndStation.isBefore(searchEndTime), trainEntity, searchDTO);

        return  (trainPassSearchStartStation.isAfter(searchStartTime) || trainPassSearchStartStation.equals(searchStartTime)) && trainPassSearchEndStation.isBefore(searchEndTime);
    }

    public boolean checkIfRouteIsCorrect(TrainEntity trainEntity, SearchDTO searchDTO){
        return (
                checkIfTrainPassStation(trainEntity, searchDTO.getStartStationId())
             && checkIfTrainPassStation(trainEntity, searchDTO.getEndStationId())
             && (findTimeTrainOnStation(trainEntity, searchDTO.getStartStationId()).isBefore(findTimeTrainOnStation(trainEntity, searchDTO.getEndStationId()))));
    }

    public boolean checkIfTrainPassStation(TrainEntity trainEntity, int stationId){

        TypedQuery<RouteEntity> query = entityManager.createQuery("select r from RouteEntity r where " +
                "r.trainEntityId = :trainId and r.stationId = :stationId", RouteEntity.class);

        return (!query.setParameter("trainId",trainEntity.getId())
                .setParameter("stationId",stationId).getResultList().isEmpty());

    }

    public LocalDateTime findTimeTrainOnStation(TrainEntity trainEntity, int stationId){

        LocalDateTime trainStart = trainEntity.getStartDateTime();

        TypedQuery<RouteEntity> query = entityManager.createQuery("select r from RouteEntity r where " +
                "r.trainEntityId = :trainId and r.stationId = :stationId", RouteEntity.class);

        int minutesFromStartStation = query.setParameter("trainId",trainEntity.getId())
                                           .setParameter("stationId",stationId).getSingleResult().getMinutesFromStartStn();

        return trainStart.plusMinutes(minutesFromStartStation);

    }

    public void persistChangedRoute(RouteEntity routeEntity){
        entityManager.merge(routeEntity);
    }

    public void addRoute (RouteEntity routeEntity){
        entityManager.persist(routeEntity);
    }

    public List<RouteEntity> findRouteOfTrain(int trainEntityId){
        Query q = entityManager.createQuery("Select r FROM RouteEntity r where r.trainEntityId = :trainEntityId", RouteEntity.class);
        return q.setParameter("trainEntityId",trainEntityId).getResultList();
    }

    public List<RouteEntity> findAll(){
        return entityManager.createQuery("Select r FROM RouteEntity r", RouteEntity.class).getResultList();
    }

    public void delete(RouteEntity routeEntity){
        entityManager.remove(routeEntity);
    }
}
