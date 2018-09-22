package persistence;

public interface Repository<KeyT, EntityT> {
    public void save(EntityT entityToSave);

    public void delete(KeyT key);

    public Object fetchOne(KeyT key);
}
