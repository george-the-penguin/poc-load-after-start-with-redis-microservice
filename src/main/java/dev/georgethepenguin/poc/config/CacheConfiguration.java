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

package dev.georgethepenguin.poc.config;

import dev.georgethepenguin.poc.client.reqres.model.Datum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * Class responsible for performing the necessary configuration for cache management.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@Configuration
public class CacheConfiguration {

    /**
     * Builds the Redis template bean for the resource data.
     *
     * @param connectionFactory the Redis connection factory.
     * @return the Redis template for the resource data.
     */
    @Bean
    public RedisTemplate<String, List<Datum>> datumRedisTemplate(RedisConnectionFactory connectionFactory) {
        final var template = new RedisTemplate<String, List<Datum>>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
