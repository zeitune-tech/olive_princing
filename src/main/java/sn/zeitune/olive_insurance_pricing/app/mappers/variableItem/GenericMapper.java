package sn.zeitune.olive_insurance_pricing.app.mappers.variableItem;

public abstract class GenericMapper<ENTITY, REQ, RES> {
    public abstract void putRequestValue(REQ dto, ENTITY entity);
    public abstract RES map(ENTITY entity);
}
