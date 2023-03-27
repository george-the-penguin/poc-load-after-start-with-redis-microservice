/*
 *  MIT License
 *
 * Copyright (c) 2023 Jorge Garcia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.georgethepenguin.poc.component;

import dev.georgethepenguin.poc.client.reqres.api.ReqresClient;
import dev.georgethepenguin.poc.client.reqres.model.Datum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * This component stores resource information in the cache and retrieves it upon request.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@Component
public class CacheComponent {

    private static final Logger LOGGER = Logger.getLogger(CacheComponent.class.getName());

    private static final String DATA_KEY = "data";

    private final int cacheTtlSeconds;

    private final RedisTemplate<String, List<Datum>> datumRedisTemplate;

    private final ReqresClient reqresClient;


    /**
     * Constructor.
     *
     * @param cacheTtlSeconds    the cache ttl seconds.
     * @param datumRedisTemplate the datum Redis template.
     * @param reqresClient       the Reqres client.
     */
    @Autowired
    public CacheComponent(@Value("${cache.ttl.seconds}") int cacheTtlSeconds,
                          RedisTemplate<String, List<Datum>> datumRedisTemplate,
                          ReqresClient reqresClient) {
        this.cacheTtlSeconds = cacheTtlSeconds;
        this.datumRedisTemplate = datumRedisTemplate;
        this.reqresClient = reqresClient;
    }

    /**
     * Gets the resource data. If the cache does not contain the resource information, then it first queries the
     * Reqres API, stores the information in the cache, and then returns it.
     *
     * @return the resource data
     */
    public List<Datum> getResourceData() {
        final var operations = datumRedisTemplate.opsForValue();
        final var data = operations.get(DATA_KEY);
        if (data != null) {
            LOGGER.log(Level.INFO, "DATA FROM CACHE: {0}", data.size());
            return data;
        }


        final var newData = reqresClient.getResources().data();
        LOGGER.log(Level.INFO, "NEW DATA FROM REQRES SERVICE: {0}", newData.size());

        operations.set(DATA_KEY, newData, cacheTtlSeconds, SECONDS);
        return newData;
    }

    /**
     * Deletes the resource data from the cache.
     */
    public void deleteResourceData() {
        final var result = datumRedisTemplate.opsForValue().getOperations().delete(DATA_KEY);
        LOGGER.log(Level.INFO, "THE CACHED DATA HAS BEEN DELETED! {0}", result);
    }
}
