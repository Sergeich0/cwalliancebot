package me.sergeich0.cwalliancebot.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import me.sergeich0.cwalliancebot.controller.bot.util.PoiType;
import me.sergeich0.cwalliancebot.entity.AlliancePointOfInterest;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class AlliancePoiRepository implements PoiRepository {

    private EntityManager entityManager;

    @Override
    public void save(AlliancePointOfInterest poi) {
        entityManager.merge(poi);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AlliancePointOfInterest> findAllPoi() {
        Query query = entityManager.createQuery("from AlliancePointOfInterest where type=:type and active=true",
                AlliancePoiRepository.class);

        query.setParameter("type", PoiType.LOCATION);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AlliancePointOfInterest> getHeadquarters() {
        Query query = entityManager.createQuery("from AlliancePointOfInterest where type=:type and active=true",
                AlliancePoiRepository.class);

        query.setParameter("type", PoiType.HEADQUARTER);
        return query.getResultList();
    }

    @Override
    public AlliancePointOfInterest getPoiByCode(String poiCode) {
        Query query = entityManager.createQuery("from AlliancePointOfInterest where code=:code",
                AlliancePoiRepository.class);

        query.setParameter("code", poiCode);
        try {
            return (AlliancePointOfInterest) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
