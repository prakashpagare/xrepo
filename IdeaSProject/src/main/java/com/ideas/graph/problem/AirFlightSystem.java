package com.ideas.graph.problem;

import java.util.List;

/**
 * Consists of a collection of 
 * Airports(Nodes) and 
 * Collection of Routes (Edges) 
 * for air flight system.
 * @author
 *
 */
public class AirFlightSystem {
		  private final List<Airport> airports;
		  private final List<Route> routes;

		  public AirFlightSystem(List<Airport> airports, List<Route> routes) {
		    this.airports = airports;
		    this.routes = routes;
		  }

		  public List<Airport> getAirports() {
		    return airports;
		  }

		  public List<Route> getRoutes() {
		    return routes;
		  }

}
