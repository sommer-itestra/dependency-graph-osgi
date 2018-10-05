/*******************************************************************************
 * Copyright (c) 2018 Amit Kumar Mondal
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package com.amitinside.dependency.graph.osgi.algo;

import java.util.List;
import java.util.Set;

import org.graphstream.algorithm.Algorithm;
import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public final class CycleFinderAlgo implements Algorithm {

    private final boolean debug;
    private Graph graph;
    private TarjanStronglyConnectedComponents tscc;
    private boolean hasCycle;

    public CycleFinderAlgo(final boolean debug) {
        this.debug = debug;
    }

    @Override
    public void init(final Graph graph) {
        this.graph = graph;
        tscc = new TarjanStronglyConnectedComponents();
    }

    @Override
    public void compute() {
        final List<Integer> sccIndices = Lists.newArrayList();
        for (final Node n : graph.getEachNode()) {
            final Integer attribute = n.getAttribute(tscc.getSCCIndexAttribute(), Integer.class);
            if (debug) {
                n.addAttribute("label", attribute);
            }
            sccIndices.add(attribute);
        }
        final Set<Integer> set = Sets.newHashSet(sccIndices);
        hasCycle = sccIndices.size() != set.size();
    }

    public boolean hasCycle() {
        return hasCycle;
    }

}
