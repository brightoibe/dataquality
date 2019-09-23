package org.openmrs.module.datamigration.util.Model;

import org.openmrs.BaseOpenmrsMetadata;

import java.util.*;

public class Encounter extends BaseOpenmrsMetadata {

    Integer encounterTypeId;

    Integer encounterLocationId;

    String encounterDate;

    Integer formTypeId;

    Set<Obs> obs = new LinkedHashSet<>();

    private Provider provider;

    public Set<Obs> getObs() {
        return obs;
    }

    public void setObs(Set<Obs> obs) {
        this.obs = obs;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Integer getEncounterTypeId() {
        return encounterTypeId;
    }

    public void setEncounterTypeId(Integer encounterTypeId) {
        this.encounterTypeId = encounterTypeId;
    }

    public Integer getEncounterLocationId() {

        return encounterLocationId;
    }

    public void setEncounterLocationId(Integer encounterLocationId) {
        this.encounterLocationId = encounterLocationId;
    }

    public String getEncounterDate() {
        return encounterDate;
    }

    public void setEncounterDate(String encounterDate) {
        this.encounterDate = encounterDate;
    }

    public Integer getFormTypeId() {
        return formTypeId;
    }

    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer integer) {

    }
}
