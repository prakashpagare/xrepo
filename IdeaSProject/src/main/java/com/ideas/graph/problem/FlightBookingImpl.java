package com.ideas.graph.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Traversal across nodes and find the shortest path will minimum edges. 
 * Assume all edges have same distance.
 * @author
 *
 */
public class FlightBookingImpl implements IFlightBooking{

		  private final List<Airport> airports;
		  private final List<Route> routes;
		  private Set<Airport> settledNodes;
		  private Set<Airport> unSettledNodes;
		  private Map<Airport, Airport> predecessors;
		  private Map<Airport, Integer> distance;

		  public FlightBookingImpl(AirFlightSystem airFlightSystem) {
		    this.airports = new ArrayList<Airport>(airFlightSystem.getAirports());
		    this.routes = new ArrayList<Route>(airFlightSystem.getRoutes());
		  }

		  /**
		   * Traverse the graph starting from source to directed unsettled nodes. 
		   * Initially all nodes will be unsettled before traversal.
		   * @param source
		   */
		  public void execute(Airport source) {
		    settledNodes = new HashSet<Airport>();
		    unSettledNodes = new HashSet<Airport>();
		    distance = new HashMap<Airport, Integer>();
		    predecessors = new HashMap<Airport, Airport>();
		    distance.put(source, 0);
		    unSettledNodes.add(source);
		    while (unSettledNodes.size() > 0) {
		      Airport node = getMinimum(unSettledNodes);
		      settledNodes.add(node);
		      unSettledNodes.remove(node);
		      findMinimumDistances(node);
		    }
		  }

		  private void findMinimumDistances(Airport node) {
		    List<Airport> adjacentNodes = getNeighborNodes(node);
		    for (Airport target : adjacentNodes) {
		      if (getShortestDistance(target) > getShortestDistance(node)
		          + getDistance(node, target)) {
		        distance.put(target, getShortestDistance(node)
		            + getDistance(node, target));
		        predecessors.put(target, node);
		        unSettledNodes.add(target);
		      }
		    }

		  }

		  private int getDistance(Airport node, Airport target) {
		    for (Route route : routes) {
		      if (route.getSource().equals(node)
		          && route.getDestination().equals(target)) {
		        return route.getDistance();
		      }
		    }
		    return 0;
		  }

		  private List<Airport> getNeighborNodes(Airport node) {
		    List<Airport> neighborNodes = new ArrayList<Airport>();
		    for (Route route : routes) {
		      if (route.getSource().equals(node)
		          && !isSettled(route.getDestination())) {
		        neighborNodes.add(route.getDestination());
		      }
		    }
		    return neighborNodes;
		  }

		  private Airport getMinimum(Set<Airport> airports) {
		    Airport minimum = null;
		    for (Airport airport : airports) {
		      if (minimum == null) {
		        minimum = airport;
		      } else {
		        if (getShortestDistance(airport) < getShortestDistance(minimum)) {
		          minimum = airport;
		        }
		      }
		    }
		    return minimum;
		  }

		  private boolean isSettled(Airport airport) {
		    return settledNodes.contains(airport);
		  }

		  private int getShortestDistance(Airport destination) {
		    Integer d = distance.get(destination);
		    if (d == null) {
		      return Integer.MAX_VALUE;
		    } else {
		      return d;
		    }
		  }

		  /*
		   * The path from the source to destination otherwise null.
		   */
		  public LinkedList<Airport> getShortestPath(Airport target) {
		    LinkedList<Airport> path = new LinkedList<Airport>();
		    Airport step = target;
		    if (predecessors.get(step) == null) {
		      return null;
		    }
		    path.add(step);
		    while (predecessors.get(step) != null) {
		      step = predecessors.get(step);
		      path.add(step);
		    }
		    /*reverse it to correct order from source to destination*/
		    Collections.reverse(path);
		    return path;
		  }

}
