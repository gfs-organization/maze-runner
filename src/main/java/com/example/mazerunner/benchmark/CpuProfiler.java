package com.example.mazerunner.benchmark;

/*-
 * #%L
 * Elastic APM Java agent
 * %%
 * Copyright (C) 2018 - 2020 Elastic and contributors
 * %%
 * Licensed to Elasticsearch B.V. under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch B.V. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * #L%
 */

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.MBeanServerConnection;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.IterationParams;
import org.openjdk.jmh.profile.InternalProfiler;
import org.openjdk.jmh.results.AggregationPolicy;
import org.openjdk.jmh.results.Defaults;
import org.openjdk.jmh.results.IterationResult;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.ScalarResult;

import com.sun.management.OperatingSystemMXBean;

public class CpuProfiler implements InternalProfiler {

    private final OperatingSystemMXBean osMxBean;
    private long beforeProcessCpuTime;

    public CpuProfiler() {
        try {
            final MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();
            osMxBean = ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void beforeIteration(final BenchmarkParams benchmarkParams, final IterationParams iterationParams) {
        beforeProcessCpuTime = osMxBean.getProcessCpuTime();
    }

    @Override
    public Collection<? extends Result> afterIteration(
            final BenchmarkParams benchmarkParams, final IterationParams iterationParams, final IterationResult result) {
        final List<ScalarResult> results = new ArrayList<>();
        final long allOps = result.getMetadata().getAllOps();

        final long totalCpuTime = osMxBean.getProcessCpuTime() - beforeProcessCpuTime;

        results.add(new ScalarResult(Defaults.PREFIX + "cpu.time.norm", (allOps != 0) ? 1.0 * totalCpuTime / allOps : Double.NaN, "ns/op",
                AggregationPolicy.AVG));
        return results;
    }

    @Override
    public String getDescription() {
        return "CPU profiling via MBeans";
    }
}