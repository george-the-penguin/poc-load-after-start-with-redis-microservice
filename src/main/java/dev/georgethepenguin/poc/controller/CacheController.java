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

package dev.georgethepenguin.poc.controller;

import dev.georgethepenguin.poc.client.reqres.model.Datum;
import dev.georgethepenguin.poc.component.CacheComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller class that allows management of information stored in the cache.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    private final CacheComponent cacheComponent;

    /**
     * Constructor.
     *
     * @param cacheComponent the cache component.
     */
    @Autowired
    public CacheController(CacheComponent cacheComponent) {
        this.cacheComponent = cacheComponent;
    }

    /**
     * Gets the resource data. If the cache does not contain the resource information, then it first queries the
     * Reqres API, stores the information in the cache, and then returns it.
     *
     * @return the resource data
     */
    @GetMapping
    public ResponseEntity<List<Datum>> getResourceData() {
        return ResponseEntity.ok(cacheComponent.getResourceData());
    }

    /**
     * Deletes the resource data from the cache.
     *
     * @return A HTTP No Content response.
     */
    @DeleteMapping
    public ResponseEntity<?> deleteResourceData() {
        cacheComponent.deleteResourceData();
        return ResponseEntity.noContent().build();
    }
}
