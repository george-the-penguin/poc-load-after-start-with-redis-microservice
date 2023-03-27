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

package dev.georgethepenguin.poc.client.reqres.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * The resources response from Reqres API.
 *
 * @param page       the page number.
 * @param perPage    the data size per page.
 * @param total      the total data size.
 * @param totalPages the total number of possible pages.
 * @param data       the resources' data.
 * @param support    the support information from Reqres API.
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
public record Resources(
        int page,
        int perPage,
        int total,
        int totalPages,
        List<Datum> data,
        Support support) implements Serializable {

    @Serial
    private static final long serialVersionUID = -580879583911643957L;
}
