package com.isep.acme.repositories.h2Repos.Repos;

import com.isep.acme.model.H2Entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Profile("h2Profile")
@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#p0.userId", condition = "#p0.userId != null"),
            @CacheEvict(key = "#p0.username", condition = "#p0.username != null")})
    <S extends User> S save(S entity);

    @Override
    @Cacheable
    Optional<User> findById(Long userId);

    @Cacheable
    default User getById(final Long userId) throws Exception {
        final Optional<User> optionalUser = findById(userId);

        if (optionalUser.isEmpty()) {
            throw new Exception("user not found");
        }
        if (!optionalUser.get().isEnabled()) {
            throw new Exception("user is disabled");
        }
        return optionalUser.get();
    }

    @Cacheable
    Optional<User> findByUsername(String username);

}