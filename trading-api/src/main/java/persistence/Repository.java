package persistence;

public interface Repository<Key, Entity> {
    public void save(Entity entityToSave);

    public void delete(Key key);

    public Object fetchOne(Key key);
}
