package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

final class ConfigurableMutableNetwork<N, E> extends ConfigurableNetwork<N, E> implements MutableNetwork<N, E> {
    ConfigurableMutableNetwork(NetworkBuilder<? super N, ? super E> builder) {
        super(builder);
    }

    @CanIgnoreReturnValue
    public boolean addNode(N node) {
        Preconditions.checkNotNull(node, "node");
        if (containsNode(node)) {
            return false;
        }
        addNodeInternal(node);
        return true;
    }

    @CanIgnoreReturnValue
    private NetworkConnections<N, E> addNodeInternal(N node) {
        NetworkConnections<N, E> connections = newConnections();
        Preconditions.checkState(this.nodeConnections.put(node, connections) == null);
        return connections;
    }

    @CanIgnoreReturnValue
    public boolean addEdge(N nodeU, N nodeV, E edge) {
        boolean z;
        boolean z2 = false;
        Preconditions.checkNotNull(nodeU, "nodeU");
        Preconditions.checkNotNull(nodeV, "nodeV");
        Preconditions.checkNotNull(edge, "edge");
        if (containsEdge(edge)) {
            EndpointPair<N> existingIncidentNodes = incidentNodes(edge);
            EndpointPair<N> newIncidentNodes = EndpointPair.m180of((Network<?, ?>) this, nodeU, nodeV);
            Preconditions.checkArgument(existingIncidentNodes.equals(newIncidentNodes), "Edge %s already exists between the following nodes: %s, so it cannot be reused to connect the following nodes: %s.", edge, existingIncidentNodes, newIncidentNodes);
            return false;
        }
        NetworkConnections<N, E> connectionsU = (NetworkConnections) this.nodeConnections.get(nodeU);
        if (!allowsParallelEdges()) {
            if (connectionsU == null || !connectionsU.successors().contains(nodeV)) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "Nodes %s and %s are already connected by a different edge. To construct a graph that allows parallel edges, call allowsParallelEdges(true) on the Builder.", (Object) nodeU, (Object) nodeV);
        }
        boolean isSelfLoop = nodeU.equals(nodeV);
        if (!allowsSelfLoops()) {
            if (!isSelfLoop) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", (Object) nodeU);
        }
        if (connectionsU == null) {
            connectionsU = addNodeInternal(nodeU);
        }
        connectionsU.addOutEdge(edge, nodeV);
        NetworkConnections<N, E> connectionsV = (NetworkConnections) this.nodeConnections.get(nodeV);
        if (connectionsV == null) {
            connectionsV = addNodeInternal(nodeV);
        }
        connectionsV.addInEdge(edge, nodeU, isSelfLoop);
        this.edgeToReferenceNode.put(edge, nodeU);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean addEdge(EndpointPair<N> endpoints, E edge) {
        validateEndpoints(endpoints);
        return addEdge(endpoints.nodeU(), endpoints.nodeV(), edge);
    }

    @CanIgnoreReturnValue
    public boolean removeNode(N node) {
        Preconditions.checkNotNull(node, "node");
        NetworkConnections<N, E> connections = (NetworkConnections) this.nodeConnections.get(node);
        if (connections == null) {
            return false;
        }
        UnmodifiableIterator<E> it = ImmutableList.copyOf(connections.incidentEdges()).iterator();
        while (it.hasNext()) {
            removeEdge(it.next());
        }
        this.nodeConnections.remove(node);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean removeEdge(E edge) {
        boolean z = false;
        Preconditions.checkNotNull(edge, "edge");
        N nodeU = this.edgeToReferenceNode.get(edge);
        if (nodeU == null) {
            return false;
        }
        NetworkConnections<N, E> connectionsU = (NetworkConnections) this.nodeConnections.get(nodeU);
        N nodeV = connectionsU.adjacentNode(edge);
        NetworkConnections<N, E> connectionsV = (NetworkConnections) this.nodeConnections.get(nodeV);
        connectionsU.removeOutEdge(edge);
        if (allowsSelfLoops() && nodeU.equals(nodeV)) {
            z = true;
        }
        connectionsV.removeInEdge(edge, z);
        this.edgeToReferenceNode.remove(edge);
        return true;
    }

    private NetworkConnections<N, E> newConnections() {
        if (isDirected()) {
            if (allowsParallelEdges()) {
                return DirectedMultiNetworkConnections.m177of();
            }
            return DirectedNetworkConnections.m178of();
        } else if (allowsParallelEdges()) {
            return UndirectedMultiNetworkConnections.m183of();
        } else {
            return UndirectedNetworkConnections.m184of();
        }
    }
}
